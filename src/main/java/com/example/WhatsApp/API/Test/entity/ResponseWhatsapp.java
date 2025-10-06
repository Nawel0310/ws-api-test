package com.example.WhatsApp.API.Test.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWhatsapp {

    private String messaging_product;
    private List<ResponseWhatsappContact> contacts;
    private List<ResponseWhatsappMessage> messages;

}
