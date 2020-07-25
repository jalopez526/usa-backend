package com.usa.model.dto.articulo;

import com.usa.model.domain.articulo.Articulo;
import java.util.List;
import com.usa.model.dto.respuesta.Respuesta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class GetArticulosResponse {
    private Respuesta respuesta;
    private List<Articulo> articulos;

}
