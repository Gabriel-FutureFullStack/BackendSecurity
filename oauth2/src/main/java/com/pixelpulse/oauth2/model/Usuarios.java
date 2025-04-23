package com.pixelpulse.oauth2.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "usuarios")
public class Usuarios implements Serializable {
    //Serializable es necesario para la serializacion y deserializacion de los objetos
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @Column
    private String nombre;
    @Column
    private String correo;
    @Column
    private String username;
    @Column
    private String password;
    //Relacion de uno a muchos con roles
    //private 
    @ManyToOne
    @JoinColumn(name="id_rol",nullable=false, foreignKey=@ForeignKey
    (foreignKeyDefinition="foreign key(id_rol) references roles_usuarios(id_rol)"))	
    private RolesUsuarios rolesUsuarios;
    @Column
    private boolean estado;
    //Constructores
    public Usuarios() {
        super();
    }
    public Usuarios(Long idUsuario, String nombre, String correo, String password, RolesUsuarios rolesUsuarios,
            boolean estado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.rolesUsuarios = rolesUsuarios;
        this.estado = estado;
    }
    //Getters and Setters son necesarios para la serializacion y deserializacion de los objetos
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public RolesUsuarios getRolesUsuarios() {
        return rolesUsuarios;
    }
    public void setRolesUsuarios(RolesUsuarios rolesUsuarios) {
        this.rolesUsuarios = rolesUsuarios;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }   
}
