package com.pixelpulse.oauth2resourse.service;

import java.util.Collection;

import com.pixelpulse.oauth2resourse.model.Productos;

public interface ProductosService {
    public abstract void insert(Productos entidad);
    public abstract Productos update(Productos entidad, Long id);
    public abstract boolean delete(Long id);
    public abstract Productos getById(Long id);
    public abstract Collection<Productos> getAll();
}
