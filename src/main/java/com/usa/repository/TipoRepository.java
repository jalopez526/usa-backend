package com.usa.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.usa.model.domain.tipo.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
    Optional<Tipo> findByDescripcion(final String descripcion);
}
