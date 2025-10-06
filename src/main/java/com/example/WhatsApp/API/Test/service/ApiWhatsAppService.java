package com.example.WhatsApp.API.Test.service;
import com.example.WhatsApp.API.Test.dto.MessageBodyDTO;
import com.example.WhatsApp.API.Test.entity.RequestMessage;
import com.example.WhatsApp.API.Test.entity.RequestMessageText;
import com.example.WhatsApp.API.Test.entity.ResponseWhatsapp;
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


    public ResponseWhatsapp sendMessage(MessageBodyDTO payload) throws JsonProcessingException {
        RequestMessage request = new RequestMessage("whatsapp", payload.getNumber(), new RequestMessageText(payload.getMessage()));

        String response = restClient.post()
                .uri("")//AGREGAR URI PERSONALIZADO
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(String.class);

        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(response, ResponseWhatsapp.class);
    }
}
