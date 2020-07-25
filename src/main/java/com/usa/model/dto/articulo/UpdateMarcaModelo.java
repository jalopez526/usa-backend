package com.usa.model.dto.articulo;

import lombok.Data;

@Data
public class UpdateMarcaModelo {
    private Long id;
    private String marca;
    private String modelo;
    private String anio;
}
