package com.example.WhatsApp.API.Test.controller;


import com.example.WhatsApp.API.Test.dto.MessageBodyDTO;
import com.example.WhatsApp.API.Test.dto.NotificacionFacturaResponseDTO;
import com.example.WhatsApp.API.Test.dto.ResponseWhatsappDTO;
import com.example.WhatsApp.API.Test.service.ApiWhatsAppService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/whatsapp")
@RequiredArgsConstructor
public class ApiWhatsAppController {

    private final ApiWhatsAppService apiWhatsAppService;

    @PostMapping("/enviar")
    public ResponseWhatsappDTO enviar(@RequestBody MessageBodyDTO payload) throws JsonProcessingException {
        return apiWhatsAppService.sendMessage(payload);
    }

    @PostMapping("/notificar-facturas")
    public ResponseEntity<List<NotificacionFacturaResponseDTO>> notificarFacturasPendientes() {
        List<NotificacionFacturaResponseDTO> resultados = apiWhatsAppService.enviarNotificacionFacturasPendientes();
        return ResponseEntity.ok(resultados);
    }

}
