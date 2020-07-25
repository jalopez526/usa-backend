package com.usa.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.usa.model.domain.articulo.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long>
{
    @Query(nativeQuery = true, value = "select distinct a.* from Articulos a inner join ArticuloMarcasModelos b on (a.Id = b.IdArticulo) where (?1 is null or a.Nombre = ?1) and (?2 is null or a.IdTipo = ?2) and (?3 is null or b.IdMarca = ?3) and (?4 is null or b.IdModelo = ?4) and (?5 is null or b.Anio = ?5) and a.estado = 1")
    List<Articulo> findArticulos(final String nombre, final Long tipo, final Long marca, final Long modelo, final String anio);
    
    @Query(nativeQuery = true, value = "select distinct a.* from articulos a inner join ArticuloMarcasModelos c ON a.id = c.IdArticulo where lower(a.nombre)= lower(:#{#articulo.nombre}) and a.estado in (1, 2) and a.IdTipo = lower(:#{#articulo.tipo.id}) and c.IdMarca in (SELECT IdMarca FROM ArticuloMarcasModelos WHERE IdArticulo = :#{#articulo.id}) and c.idModelo in (SELECT IdModelo FROM ArticuloMarcasModelos WHERE IdArticulo = :#{#articulo.id}) and c.Anio in (SELECT anio FROM ArticuloMarcasModelos WHERE IdArticulo = :#{#articulo.id}) ")
    List<Articulo> findSimilarArticulos(@Param("articulo") final Articulo articulo);
    
    List<Articulo> findArticuloByEstado(final Integer estado);
}
