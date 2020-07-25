package com.usa.model.domain.despacho;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.usa.model.domain.login.Usuario;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "Despachos", schema = "dbo")
@Data
public class Despacho
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "IdUsuario", referencedColumnName = "Id", nullable = false)
    private Usuario usuario;
    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;
    @Column(name = "Estado", nullable = false, columnDefinition = "integer default 1")
    private Integer estado;
    @OneToMany(mappedBy = "despacho")
    private List<DespachoDetalle> despachoDetalles;
}
