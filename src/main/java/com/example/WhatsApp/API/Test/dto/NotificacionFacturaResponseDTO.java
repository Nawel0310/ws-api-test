package com.example.WhatsApp.API.Test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionFacturaResponseDTO {
    private Long usuarioId;
    private String numeroTelefono;
    private String nombreCompleto;
    private Integer cantidadFacturas;
    private String estado;
    private String mensaje;
}