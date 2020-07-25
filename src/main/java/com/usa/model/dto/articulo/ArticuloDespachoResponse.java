package com.usa.model.dto.articulo;

import java.util.List;
import com.usa.model.dto.respuesta.Respuesta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ArticuloDespachoResponse
{
    private Respuesta respuesta;
    private List<ArticuloDespacho> articuloDespachos;
}
