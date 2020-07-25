package com.usa.model.dto.articulo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ArticuloRequest
{
    private String nombre;
    private String tipo;
    private String codigo;
    private boolean esNuevo;
    private String ubicacion;
    private String precioPorMayor;
    private String precioDetalle;
    private String precioAlterno;
    private List<MarcaModelo> marcaModelo;
}
