package com.example.WhatsApp.API.Test.config;

import com.example.WhatsApp.API.Test.entity.Factura;
import com.example.WhatsApp.API.Test.entity.Usuario;
import com.example.WhatsApp.API.Test.repository.FacturaRepository;
import com.example.WhatsApp.API.Test.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final FacturaRepository facturaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0 && facturaRepository.count() == 0) {
            log.info("Inicializando datos de prueba...");

            // Usuario 1
            Usuario usuario1 = new Usuario();
            usuario1.setNombre("Nahuel");
            usuario1.setApellido("Barbaro");
            usuario1.setNumeroTelefono("54111526794405");
            usuario1.setFacturaPendiente(true);

            Factura factura1 = new Factura();
            factura1.setPrecio(new BigDecimal("150.50"));
            factura1.setFechaVencimiento(LocalDate.now().plusDays(30));
            usuario1.addFactura(factura1);

            Factura factura2 = new Factura();
            factura2.setPrecio(new BigDecimal("200.00"));
            factura2.setFechaVencimiento(LocalDate.now().plusDays(45));
            usuario1.addFactura(factura2);

            // Usuario 2
            Usuario usuario2 = new Usuario();
            usuario2.setNombre("Sonia");
            usuario2.setApellido("Fernandez");
            usuario2.setNumeroTelefono("numero-ficticio");
            usuario2.setFacturaPendiente(true);

            Factura factura3 = new Factura();
            factura3.setPrecio(new BigDecimal("180.50"));
            factura3.setFechaVencimiento(LocalDate.now().plusDays(15));
            usuario2.addFactura(factura3);

            Factura factura4 = new Factura();
            factura4.setPrecio(new BigDecimal("300.00"));
            factura4.setFechaVencimiento(LocalDate.now().plusDays(20));
            usuario2.addFactura(factura4);


            usuarioRepository.save(usuario1);
            usuarioRepository.save(usuario2);

            log.info("Datos de prueba inicializados correctamente:");
            log.info("- {} usuarios creados", usuarioRepository.count());
            log.info("- {} facturas creadas", facturaRepository.count());
        } else {
            log.info("La base de datos ya contiene datos. Omitiendo inicialización.");
        }
    }
}