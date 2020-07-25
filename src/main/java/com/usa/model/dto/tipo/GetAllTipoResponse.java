package com.usa.model.dto.tipo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.usa.model.domain.tipo.Tipo;
import com.usa.model.dto.respuesta.Respuesta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class GetAllTipoResponse {
    private Respuesta respuesta;
    private List<Tipo> tipos;
}
