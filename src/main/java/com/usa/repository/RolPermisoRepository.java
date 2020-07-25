package com.usa.repository;

import org.springframework.stereotype.Repository;
import com.usa.model.domain.login.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, Long> {

}
