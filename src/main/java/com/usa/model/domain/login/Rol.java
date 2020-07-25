package com.usa.model.domain.login;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Roles", schema = "dbo")
@Data
public class Rol
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @Column(name = "Descripcion", nullable = false)
    private String descripcion;
    @Column(name = "NombreRol", nullable = false)
    private String nombreRol;
    @OneToMany(mappedBy = "rol")
    private List<RolPermiso> rolPermisos;

}
