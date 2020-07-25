
package com.usa.repository;

import java.util.List;
import com.usa.model.domain.marca.Marca;
import org.springframework.stereotype.Repository;
import com.usa.model.domain.modelo.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    List<Modelo> findByMarca(final Marca marca);
}
