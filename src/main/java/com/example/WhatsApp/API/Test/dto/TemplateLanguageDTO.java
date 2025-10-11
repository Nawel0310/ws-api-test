package com.example.WhatsApp.API.Test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateLanguageDTO {

    private String code;

    public static TemplateLanguageDTO spanishArgentina() {
        return new TemplateLanguageDTO("es_AR");
    }
}