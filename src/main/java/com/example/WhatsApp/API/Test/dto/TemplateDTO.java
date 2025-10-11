package com.example.WhatsApp.API.Test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDTO {

    private String name;

    private TemplateLanguageDTO language;

    private List<TemplateComponentDTO> components;
}