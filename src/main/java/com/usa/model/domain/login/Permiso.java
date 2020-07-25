package com.usa.model.domain.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Permisos", schema = "dbo")
@Data
public class Permiso {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Column(name = "Descripcion", nullable = false)
    private String descripcion;
    @OneToMany(mappedBy = "permiso")
    @JsonIgnore
    private List<RolPermiso> rolPermisos;

}
