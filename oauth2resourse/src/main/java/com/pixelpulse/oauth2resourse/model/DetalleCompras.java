package com.pixelpulse.oauth2resourse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class DetalleCompras {
    @EmbeddedId
    private DetalleComprasKey id;

    @ManyToOne
    @MapsId("idCompra")
    @JoinColumn(name = "id_compra")
    @JsonBackReference
    private Compras compras;

    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto")
    private Productos productos;

    @Column
    private Integer cantidad;
    @Column
    private Double precioUnitario;
    @Column
    private Double subtotal;
    public DetalleCompras() {
        super();
    }
    public DetalleCompras(DetalleComprasKey id, Compras compras, Productos productos, Integer cantidad,
            Double precioUnitario, Double subtotal) {
        this.id = id;
        this.compras = compras;
        this.productos = productos;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
    public DetalleComprasKey getId() {
        return id;
    }
    public void setId(DetalleComprasKey id) {
        this.id = id;
    }
    public Compras getCompras() {
        return compras;
    }
    public void setCompras(Compras compras) {
        this.compras = compras;
    }
    public Productos getProductos() {
        return productos;
    }
    public void setProductos(Productos productos) {
        this.productos = productos;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
}
