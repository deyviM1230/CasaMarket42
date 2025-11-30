package com.example.demostrategy.strategy;

import com.example.demostrategy.model.DatosNotificacion;

public interface INotificador {
    void enviar(DatosNotificacion data);
}
