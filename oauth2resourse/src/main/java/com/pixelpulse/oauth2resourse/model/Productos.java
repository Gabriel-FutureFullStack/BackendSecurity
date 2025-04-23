package com.pixelpulse.oauth2resourse.model;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = "productos")
public class Productos{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private Double precioUnitario;
    @Column
    private boolean estado;

    @OneToMany(mappedBy = "productos", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleCompras> detalleCompras = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name="id_proveedor",nullable=false, foreignKey=@ForeignKey
	(foreignKeyDefinition="foreign key(id_proveedor) references proveedores(id_proveedor)"))
    private Proveedores proveedores;
    
    public Productos() {
        super();
    }
    public Productos(Long idProducto, String nombre, String descripcion, Double precioUnitario, Proveedores proveedores,
            Set<DetalleCompras> detalleCompras) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.proveedores = proveedores;
        this.detalleCompras = detalleCompras;
    }
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Double getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    //DetalleCompras
    // public Set<DetalleCompras> getDetalleCompras() {
    //     return detalleCompras;
    // }
    // public void setDetalleCompras(Set<DetalleCompras> detalleCompras) {
    //     this.detalleCompras = detalleCompras;
    // }
    //Proveedores
    public Proveedores getProveedores() {
        return proveedores;
    }
    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }
}
