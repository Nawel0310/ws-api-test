package com.example.WhatsApp.API.Test.repository;

import com.example.WhatsApp.API.Test.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNumeroTelefono(String numeroTelefono);
}