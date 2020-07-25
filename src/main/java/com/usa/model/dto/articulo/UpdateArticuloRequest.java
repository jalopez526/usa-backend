package com.usa.model.dto.articulo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UpdateArticuloRequest {
    private String nombre;
    private String tipo;
    private String codigo;
    private boolean esNuevo;
    private String ubicacion;
    private String precioPorMayor;
    private String precioDetalle;
    private String precioAlterno;
    private List<UpdateMarcaModelo> marcaModelo;

}
