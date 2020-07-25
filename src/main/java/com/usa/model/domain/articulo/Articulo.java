package com.usa.model.domain.articulo;

import com.usa.model.domain.tipo.Tipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Articulos", schema = "dbo")
@Data
@Builder
@AllArgsConstructor
public class Articulo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Codigo", nullable = false)
    private String codigo;
    @Column(name = "Ubicacion", nullable = false)
    private String ubicacion;
    @Column(name = "PrecioPorMayor", nullable = false)
    private BigDecimal precioPorMayor;
    @Column(name = "PrecioDetalle", nullable = false)
    private BigDecimal precioDetalle;
    @Column(name = "PrecioAlterno", nullable = false)
    private BigDecimal precioAlterno;
    @Column(name = "EsNuevo", nullable = false)
    private boolean esNuevo;
    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;
    @Column(name = "Estado", nullable = false, columnDefinition = "integer default 1")
    private Integer estado;
    @OneToOne
    @JoinColumn(name = "IdTipo", referencedColumnName = "Id", nullable = false)
    private Tipo tipo;

    public Articulo () { }
}
