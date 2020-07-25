package com.usa.model.dto.articulo;

import com.usa.model.domain.articulo.Articulo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticuloDespacho {
    private Articulo articulo;
    private Integer cantidad;
}
