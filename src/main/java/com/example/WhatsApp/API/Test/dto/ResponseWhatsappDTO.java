package com.example.WhatsApp.API.Test.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWhatsappDTO {

    private String messaging_product;
    private List<ResponseWhatsappContactDTO> contacts;
    private List<ResponseWhatsappMessageDTO> messages;

}