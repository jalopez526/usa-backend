

package com.usa.repository;

import org.springframework.stereotype.Repository;
import com.usa.model.domain.marca.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
