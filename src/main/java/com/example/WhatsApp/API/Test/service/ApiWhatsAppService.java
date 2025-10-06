package com.example.WhatsApp.API.Test.service;
import com.example.WhatsApp.API.Test.dto.MessageBodyDTO;
import com.example.WhatsApp.API.Test.dto.RequestMessageDTO;
import com.example.WhatsApp.API.Test.dto.RequestMessageTextDTO;
import com.example.WhatsApp.API.Test.dto.ResponseWhatsappDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ApiWhatsAppService {

    private final RestClient restClient;

    public ApiWhatsAppService(
            @Value("${WHATSAPP_IDENTIFICADOR}") String identificador,
            @Value("${WHATSAPP_TOKEN}") String token
    ) {
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
}
