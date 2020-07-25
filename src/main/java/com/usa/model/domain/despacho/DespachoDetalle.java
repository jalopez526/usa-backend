package com.usa.model.domain.despacho;

import javax.persistence.OneToOne;
import com.usa.model.domain.articulo.Articulo;
import javax.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "DespachoDetalle", schema = "dbo")
@Data
public class DespachoDetalle
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "IdDespacho", referencedColumnName = "Id", nullable = false)
    private Despacho despacho;
    @OneToOne
    @JoinColumn(name = "IdArticulo", referencedColumnName = "Id", nullable = false)
    private Articulo articulo;
    
    public DespachoDetalle() {

    }
}
