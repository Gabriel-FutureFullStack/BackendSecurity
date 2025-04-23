package com.pixelpulse.oauth2resourse.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "compras" )
public class Compras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;
    @Column
    private Long idUsuario;
    @Column
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Column
    private Double total;
    @OneToMany(mappedBy = "compras", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<DetalleCompras> detalleCompras = new HashSet<>();

    @PrePersist
    public void prePersist() {
        fechaCompra= new Date();
    }
    public String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaCompra);
    }
    
    public Compras() {
        super();
    }
    public Compras(Long idCompra, Long idUsuario, Date fechaCompra, Double total) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }
    public Long getIdCompra() {
        return idCompra;
    }
    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Date getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public Set<DetalleCompras> getDetalleCompras() {
        return detalleCompras;
    }
    public void setDetalleCompras(Set<DetalleCompras> detalleCompras) {
        this.detalleCompras = detalleCompras;
    }
}
