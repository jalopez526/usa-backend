package com.usa.model.dto.articulo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.usa.model.dto.respuesta.Respuesta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class SaveArticuloResponse {
    private Respuesta respuesta;
    private List<ArticuloResponse> response;
}
