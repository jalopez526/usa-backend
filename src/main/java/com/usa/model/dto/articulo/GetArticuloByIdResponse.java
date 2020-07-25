package com.usa.model.dto.articulo;

import com.usa.model.domain.articulo.Articulo;
import com.usa.model.dto.respuesta.Respuesta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class GetArticuloByIdResponse {
    private Respuesta respuesta;
    private Articulo articulo;
}
