package com.pixelpulse.oauth2resourse.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DetalleComprasKey implements Serializable{
    @Column
    private Long idCompra;
    @Column
    private Long idProducto;
    public DetalleComprasKey() {
        super();
    }
    public DetalleComprasKey(Long idCompra, Long idProducto) {
        this.idCompra = idCompra;
        this.idProducto = idProducto;
    }
    public Long getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleComprasKey that = (DetalleComprasKey) o;
        return Objects.equals(idCompra, that.idCompra) && Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra, idProducto);
    }
}
