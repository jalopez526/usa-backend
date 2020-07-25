package com.usa.model.domain.modelo;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.usa.model.domain.marca.Marca;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "Modelos", schema = "dbo")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class Modelo
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @Column(name = "Descripcion", nullable = false)
    private String descripcion;
    @JsonIgnore
    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;
    @JsonIgnore
    @Column(name = "Estado", nullable = false)
    private boolean estado;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "IdMarca", nullable = false)
    private Marca marca;
    
    public Modelo(final String descripcion, final Date fechaCreacion, final boolean estado, final Marca marca) {
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.marca = marca;
    }
    
    private Modelo() {
    }
}
