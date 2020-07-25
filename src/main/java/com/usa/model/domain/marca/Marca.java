package com.usa.model.domain.marca;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.usa.model.domain.modelo.Modelo;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Marcas", schema = "dbo")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
public class Marca
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
    @OneToMany(mappedBy = "marca")
    private List<Modelo> modelos;
    
    public Marca(final String descripcion, final Date fechaCreacion, final boolean estado) {
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }
    
    private Marca() {
    }
}
