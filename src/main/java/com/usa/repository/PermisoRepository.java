

package com.usa.repository;

import org.springframework.stereotype.Repository;
import com.usa.model.domain.login.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
}
