package com.example.WhatsApp.API.Test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateParameterDTO {

    private String type;

    @JsonProperty("parameter_name")
    private String parameterName;

    private String text;

    public static TemplateParameterDTO text(String parameterName, String text) {
        return new TemplateParameterDTO("text", parameterName, text);
    }
}