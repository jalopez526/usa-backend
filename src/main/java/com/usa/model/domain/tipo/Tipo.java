package com.usa.model.domain.tipo;

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
@Table(name = "Tipos", schema = "dbo")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class Tipo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @Column(name = "Descripcion", nullable = false, unique = true)
    private String descripcion;
    @JsonIgnore
    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;
    @JsonIgnore
    @Column(name = "Estado", nullable = false)
    private boolean estado;

    public Tipo() {
    }

    public Tipo(final String descripcion, final Date fechaCreacion, final boolean estado) {
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }
}
