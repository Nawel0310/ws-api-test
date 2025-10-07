package com.example.WhatsApp.API.Test.repository;

import com.example.WhatsApp.API.Test.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}