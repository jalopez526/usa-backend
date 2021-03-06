package com.usa.model.dto.articulo;

import com.usa.model.domain.articulo.ArticuloMarcaModelo;
import java.util.List;
import com.usa.model.domain.articulo.Articulo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ArticuloResponse {
    private Articulo articulo;
    private List<ArticuloMarcaModelo> articuloMarcasModelos;
}
