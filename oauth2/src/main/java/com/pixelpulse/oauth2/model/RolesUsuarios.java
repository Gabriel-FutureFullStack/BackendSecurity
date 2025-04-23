package com.pixelpulse.oauth2.model;

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
@Table(name= "roles_usuarios")
public class RolesUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    @Column
    private String nombreRol;
    @OneToMany(mappedBy="rolesUsuarios", cascade=CascadeType.ALL, orphanRemoval=true)
    private Collection<Usuarios> itemsUsuarios=new ArrayList<>();

    public RolesUsuarios() {
        super();
    }
    public RolesUsuarios(Long idRol, String nombreRol, Collection<Usuarios> itemsUsuarios) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.itemsUsuarios = itemsUsuarios;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Long getIdRol() {
        return idRol;
    }
    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }
    public String getNombreRol() {
        return nombreRol;
    }
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
