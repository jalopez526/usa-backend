package com.usa.model.dto.despacho;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.usa.model.domain.despacho.Despacho;
import com.usa.model.dto.respuesta.Respuesta;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class CreateDespachoResponse {
    private Respuesta respuesta;
    private Despacho despacho;
}
