package com.example.WhatsApp.API.Test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateComponentDTO {

    private String type;

    private List<TemplateParameterDTO> parameters;

    public static TemplateComponentDTO body(List<TemplateParameterDTO> parameters) {
        return new TemplateComponentDTO("body", parameters);
    }
}