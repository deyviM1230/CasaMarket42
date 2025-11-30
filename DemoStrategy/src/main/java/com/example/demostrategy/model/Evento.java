package com.example.demostrategy.model;

public class Evento {
    private TipoEvento tipo;
    private DatosNotificacion data;

    public Evento(TipoEvento tipo, DatosNotificacion data) {
        this.tipo = tipo;
        this.data = data;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public DatosNotificacion getData() {
        return data;
    }
}
