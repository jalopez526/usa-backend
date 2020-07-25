package com.usa.model.dto.articulo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SaveArticuloRequest  {
    private ArticuloRequest articulo;
    private Integer cantidad;
}
