package com.usa.repository;

import org.springframework.stereotype.Repository;
import com.usa.model.domain.login.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsuario(final String usuario);
}
