package com.usa.model.dto.marca;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.usa.model.domain.modelo.Modelo;
import com.usa.model.dto.respuesta.Respuesta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ModelosByMarcaResponse {
    private Respuesta respuesta;
    private List<Modelo> modelos;
}
