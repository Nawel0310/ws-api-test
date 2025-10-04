package com.example.WhatsApp.API.Test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageBodyDTO {
    private String number;
    private String message;
}
