package com.usa.model.dto.articulo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.usa.model.domain.articulo.ArticuloMarcaModelo;
import com.usa.model.dto.respuesta.Respuesta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class MarcaModeloResponse {
    private Respuesta respuesta;
    private List<ArticuloMarcaModelo> articuloMarcaModelos;
}
