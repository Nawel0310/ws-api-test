package com.example.WhatsApp.API.Test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMessage {
    private String messaging_product;
    private String to;
    private RequestMessageText text;
}
