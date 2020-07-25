package com.usa.model.dto.despacho;

import com.usa.model.domain.despacho.Despacho;
import java.util.List;
import com.usa.model.dto.respuesta.Respuesta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class GetDespachoResponse {
    private Respuesta respuesta;
    private List<Despacho> despachos;
}
