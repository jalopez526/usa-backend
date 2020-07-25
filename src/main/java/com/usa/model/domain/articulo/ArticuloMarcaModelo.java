package com.usa.model.domain.articulo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usa.model.domain.marca.Marca;
import com.usa.model.domain.modelo.Modelo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ArticuloMarcasModelos", schema = "dbo")
@Data
@Builder
@AllArgsConstructor
public class ArticuloMarcaModelo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @OneToOne(cascade = { CascadeType.ALL })
    @JsonIgnore
    @JoinColumn(name = "IdArticulo", referencedColumnName = "Id", nullable = false)
    private Articulo articulo;
    @OneToOne
    @JoinColumn(name = "IdMarca", referencedColumnName = "Id", nullable = false)
    private Marca marca;
    @Column(name = "Anio", nullable = false)
    private String anio;
    @OneToOne
    @JoinColumn(name = "IdModelo", referencedColumnName = "Id", nullable = false)
    private Modelo modelo;

    public ArticuloMarcaModelo () {}
}
