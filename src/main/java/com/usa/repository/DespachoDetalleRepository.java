package com.usa.repository;

import org.springframework.stereotype.Repository;
import com.usa.model.domain.despacho.DespachoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DespachoDetalleRepository extends JpaRepository<DespachoDetalle, Long> {
}
