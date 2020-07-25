package com.usa.model.domain.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Usuarios", schema = "dbo")
@Data
public class Usuario
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Long id;
    @Column(name = "Usuario", nullable = false, unique = true)
    private String usuario;
    @Column(name = "Clave", nullable = false)
    @JsonIgnore
    private String password;
    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;
    @Column(name = "Estado", nullable = false)
    private Integer estado;
    @Column(name = "Bloqueado")
    private boolean bloqueado;
    @ManyToOne
    @JoinColumn(name = "RolId", nullable = false)
    private Rol rol;
    
    public Usuario() {
        this.fechaCreacion = new Date();
        this.estado = 1;
        this.bloqueado = false;
    }
}
