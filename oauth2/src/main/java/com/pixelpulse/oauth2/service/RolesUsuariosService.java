package com.pixelpulse.oauth2.service;

import java.util.Collection;

import com.pixelpulse.oauth2.model.RolesUsuarios;

public interface RolesUsuariosService {
    public abstract void createRolIfNotExists();
    public abstract void insert(RolesUsuarios entidad);
    public abstract RolesUsuarios update(RolesUsuarios entidad, Long id);
    public abstract boolean delete(Long id);
    public abstract RolesUsuarios getById(Long id);
    public abstract Collection<RolesUsuarios> getAll();
}
