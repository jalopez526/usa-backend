package com.usa.repository;

import org.springframework.stereotype.Repository;
import com.usa.model.domain.despacho.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DespachoRepository extends JpaRepository<Despacho, Long> {
}
