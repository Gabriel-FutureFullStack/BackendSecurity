package com.pixelpulse.oauth2resourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixelpulse.oauth2resourse.model.Productos;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
}
