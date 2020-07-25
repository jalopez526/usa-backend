package com.usa.enums;

public enum EstadoArticulo {
    CREADO(0),
    DESPACHANDO(1),
    DESPACHADO(2);

    EstadoArticulo(int estado) {
        this.estado = estado;
    }
    private int estado;

    public int get() {
        return this.estado;
    }
}
