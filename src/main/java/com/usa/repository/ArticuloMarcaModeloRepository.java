package com.usa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import javax.transaction.Transactional;
import java.util.List;
import com.usa.model.domain.articulo.Articulo;
import org.springframework.stereotype.Repository;
import com.usa.model.domain.articulo.ArticuloMarcaModelo;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ArticuloMarcaModeloRepository extends JpaRepository<ArticuloMarcaModelo, Long> {
    List<ArticuloMarcaModelo> findByArticulo(final Articulo articulo);

    @Transactional
    @Modifying
    @Query("delete from ArticuloMarcaModelo m where m.articulo=:articulo")
    void deleteByArticulo(final Articulo articulo);
}
