package com.example.WhatsApp.API.Test.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWhatsappDTO {

    private String messaging_product;
    private List<ResponseWhatsappContactDTO> contacts;
    private List<ResponseWhatsappMessageDTO> messages;

}