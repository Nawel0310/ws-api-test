package com.example.WhatsApp.API.Test.controller;


import com.example.WhatsApp.API.Test.dto.MessageBodyDTO;
import com.example.WhatsApp.API.Test.dto.ResponseWhatsappDTO;
import com.example.WhatsApp.API.Test.service.ApiWhatsAppService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/whatsapp")
@RequiredArgsConstructor
public class ApiWhatsAppController {

    private final ApiWhatsAppService apiWhatsAppService;

    @PostMapping("/enviar")
    public ResponseWhatsappDTO enviar(@RequestBody MessageBodyDTO payload) throws JsonProcessingException {
        return apiWhatsAppService.sendMessage(payload);
    }

}
