package com.usa.repository;

import org.springframework.stereotype.Repository;
import com.usa.model.domain.login.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
