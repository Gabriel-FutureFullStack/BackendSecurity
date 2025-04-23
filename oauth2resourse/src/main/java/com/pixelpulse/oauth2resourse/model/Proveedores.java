package com.pixelpulse.oauth2resourse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedores")
public class Proveedores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;
    @Column
    private String nombreEmpresa;
    @Column
    private String contactoNombre;
    @Column
    private String telefono;
    @Column
    private String correo;
    @Column
    private String direccion;
    //Relacion de uno a muchos con productos
    @OneToMany(mappedBy = "proveedores", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Productos> itemsProductos=new ArrayList<>();

    public Proveedores() {
        super();
    }

    public Proveedores(Long idProveedor, String nombreEmpresa, String contactoNombre, String telefono, String correo,
            String direccion) {
        this.idProveedor = idProveedor;
        this.nombreEmpresa = nombreEmpresa;
        this.contactoNombre = contactoNombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Long getIdProveedor() {
        return idProveedor;
    }
    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    public String getContactoNombre() {
        return contactoNombre;
    }
    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
