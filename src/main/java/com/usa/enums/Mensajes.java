package com.usa.enums;

import com.usa.model.dto.respuesta.Respuesta;

public enum Mensajes {
    OK("0", "OK"),
    NO_SE_ENCONTRARON_DATOS("1", "No se encontraron datos"),
    DATOS_INCORRECTOS("2", "Algunos de los datos introducidos no est\u00e1n correctos"),
    ERROR_INESPERADO ("3", "Ha ocurrido un error inesperado"),
    CANTIDAD_ARTICULOS_NO_DISPONIBLE("4", "La cantidad de articulos solicitados para despachar ya no se encuentra disponible"),
    USUARIO_O_CONTRASENA_INCORRECTA("5", "Usuario o contraseña incorrecto");


    private Respuesta respuesta = null;

    Mensajes(String codigo, String descripcion) {
        respuesta = new Respuesta(codigo, descripcion);
    }

    public Respuesta get() {
        return this.respuesta;
    }
}
