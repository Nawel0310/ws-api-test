package com.example.WhatsApp.API.Test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateMessageDTO {

    @JsonProperty("messaging_product")
    private String messagingProduct;

    private String to;

    private String type;

    private TemplateDTO template;

    public TemplateMessageDTO(String to, TemplateDTO template) {
        this.messagingProduct = "whatsapp";
        this.to = to;
        this.type = "template";
        this.template = template;
    }
}