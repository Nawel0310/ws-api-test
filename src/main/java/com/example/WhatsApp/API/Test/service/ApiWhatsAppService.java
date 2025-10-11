package com.example.WhatsApp.API.Test.service;
import com.example.WhatsApp.API.Test.dto.*;
import com.example.WhatsApp.API.Test.entity.Factura;
import com.example.WhatsApp.API.Test.entity.Usuario;
import com.example.WhatsApp.API.Test.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ApiWhatsAppService {

    private final RestClient restClient;
    private final UsuarioRepository usuarioRepository;
    private final String linkPago;

    public ApiWhatsAppService(
            @Value("${WHATSAPP_IDENTIFICADOR}") String identificador,
            @Value("${WHATSAPP_TOKEN}") String token,
            @Value("${WHATSAPP_LINK_PAGO:https://ejemplo.com/pagar}") String linkPago,
            UsuarioRepository usuarioRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.linkPago = linkPago;
        restClient = RestClient.builder()
                .baseUrl("https://graph.facebook.com/v17.0/" + identificador + "/messages")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }


    public ResponseWhatsappDTO sendMessage(MessageBodyDTO payload) throws JsonProcessingException {
        RequestMessageDTO request = new RequestMessageDTO("whatsapp", payload.getNumber(), new RequestMessageTextDTO(payload.getMessage()));

        String response = restClient.post()
                .uri("")//AGREGAR URI PERSONALIZADO
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(String.class);

        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(response, ResponseWhatsappDTO.class);
    }

    public ResponseWhatsappDTO sendTemplateMessage(TemplateMessageDTO templateMessage) throws JsonProcessingException {
        String response = restClient.post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(templateMessage)
                .retrieve()
                .body(String.class);

        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(response, ResponseWhatsappDTO.class);
    }

    public List<NotificacionFacturaResponseDTO> enviarNotificacionFacturasPendientes() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<NotificacionFacturaResponseDTO> resultados = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Usuario usuario : usuarios) {
            if (usuario.getFacturas() != null && !usuario.getFacturas().isEmpty()) {
                try {
                    // Construir texto de facturas pendientes
                    StringBuilder facturasTexto = new StringBuilder();
                    BigDecimal total = BigDecimal.ZERO;
                    int contador = 1;

                    for (Factura factura : usuario.getFacturas()) {
                        if (contador > 1) {
                            facturasTexto.append(" ");
                        }
                        facturasTexto.append(String.format("Factura %d: - Monto: $%.2f - Vencimiento: %s.",
                                contador++,
                                factura.getPrecio(),
                                factura.getFechaVencimiento().format(formatter)));
                        total = total.add(factura.getPrecio());
                    }

                    // Construir parámetros del template
                    List<TemplateParameterDTO> parameters = new ArrayList<>();
                    parameters.add(TemplateParameterDTO.text("nombre", usuario.getNombre()));
                    parameters.add(TemplateParameterDTO.text("apellido", usuario.getApellido()));
                    parameters.add(TemplateParameterDTO.text("facturas_pendientes", facturasTexto.toString()));
                    parameters.add(TemplateParameterDTO.text("total_a_pagar", String.format("%.2f", total)));

                    // Crear componente body con los parámetros
                    TemplateComponentDTO bodyComponent = TemplateComponentDTO.body(parameters);

                    // Crear template
                    TemplateDTO template = new TemplateDTO(
                            "factura",
                            TemplateLanguageDTO.spanishArgentina(),
                            List.of(bodyComponent)
                    );

                    // Crear mensaje con template
                    TemplateMessageDTO templateMessage = new TemplateMessageDTO(
                            usuario.getNumeroTelefono(),
                            template
                    );

                    // Enviar mensaje
                    sendTemplateMessage(templateMessage);

                    // Agregar resultado
                    NotificacionFacturaResponseDTO response = new NotificacionFacturaResponseDTO(
                            usuario.getId(),
                            usuario.getNumeroTelefono(),
                            usuario.getNombre() + " " + usuario.getApellido(),
                            usuario.getFacturas().size(),
                            "ENVIADO",
                            "Notificación enviada exitosamente"
                    );
                    resultados.add(response);

                    log.info("Notificación enviada a {} ({}) - {} facturas - Total: ${}",
                            usuario.getNombre() + " " + usuario.getApellido(),
                            usuario.getNumeroTelefono(),
                            usuario.getFacturas().size(),
                            total);

                } catch (Exception e) {
                    NotificacionFacturaResponseDTO response = new NotificacionFacturaResponseDTO(
                            usuario.getId(),
                            usuario.getNumeroTelefono(),
                            usuario.getNombre() + " " + usuario.getApellido(),
                            usuario.getFacturas() != null ? usuario.getFacturas().size() : 0,
                            "ERROR",
                            "Error al enviar notificación: " + e.getMessage()
                    );
                    resultados.add(response);
                    log.error("Error al enviar notificación a {}: {}", usuario.getNumeroTelefono(), e.getMessage());
                }
            } else {
                log.info("Usuario {} no tiene facturas pendientes. Omitiendo notificación.", usuario.getNombre());
            }
        }

        return resultados;
    }
}
