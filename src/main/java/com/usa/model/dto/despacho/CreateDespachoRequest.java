package com.usa.model.dto.despacho;

import lombok.Data;

import java.util.List;

@Data
public class CreateDespachoRequest {
    private List<ArticuloDespacho> articuloDespachos;
}
