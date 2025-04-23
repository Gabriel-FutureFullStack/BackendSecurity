package com.pixelpulse.oauth2.service;

import java.util.Collection;

import com.pixelpulse.oauth2.model.Usuarios;

public interface UsuariosService {
    public abstract void createUserIfNotExists();
    public abstract void insert(Usuarios entidad);
    public abstract Usuarios update(Usuarios entidad, Long id);
    public abstract boolean delete(Long id);
    public abstract Usuarios getById(Long id);
    public abstract Collection<Usuarios> getAll();
}
