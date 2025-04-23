package com.pixelpulse.oauth2resourse.service;

import java.util.Collection;

import com.pixelpulse.oauth2resourse.model.Compras;

public interface ComprasService {
    public abstract void insert(Compras entidad);
    public abstract Compras update(Compras entidad, Long id);
    public abstract boolean delete(Long id);
    public abstract Compras getById(Long id);
    public abstract Collection<Compras> getAll();
}
