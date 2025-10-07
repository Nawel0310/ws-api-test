package com.example.WhatsApp.API.Test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(name = "numero_telefono", nullable = false, unique = true)
    private String numeroTelefono;

    @Column(name = "factura_pendiente")
    private Boolean facturaPendiente;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Factura> facturas = new ArrayList<>();

    public void addFactura(Factura factura) {
        facturas.add(factura);
        factura.setUsuario(this);
    }

    public void removeFactura(Factura factura) {
        facturas.remove(factura);
        factura.setUsuario(null);
    }
}