package com.pixelpulse.oauth2resourse.service;

import java.util.Collection;

import com.pixelpulse.oauth2resourse.model.Proveedores;

public interface ProveedoresService {
    public abstract void insert(Proveedores entidad);
    public abstract Proveedores update(Proveedores entidad, Long id);
    public abstract boolean delete(Long id);
    public abstract Proveedores getById(Long id);
    public abstract Collection<Proveedores> getAll();
}
