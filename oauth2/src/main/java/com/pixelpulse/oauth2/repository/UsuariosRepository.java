package com.pixelpulse.oauth2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixelpulse.oauth2.model.Usuarios;


public interface UsuariosRepository extends JpaRepository<Usuarios,Long>{
    Optional<Usuarios> findByUsername(String username);
}
