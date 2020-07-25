package com.usa.model.domain.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "RolPermisos", schema = "dbo")
@Data
public class RolPermiso
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "RolId", nullable = false)
    private Rol rol;
    @ManyToOne
    @JoinColumn(name = "PermisoId", nullable = false)
    private Permiso permiso;
}
