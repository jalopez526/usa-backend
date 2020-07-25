package com.usa.model.dto.marca;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.usa.model.domain.marca.Marca;
import com.usa.model.dto.respuesta.Respuesta;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class MarcaByIdResponse {
    private Respuesta respuesta;
    private Marca marca;
}
