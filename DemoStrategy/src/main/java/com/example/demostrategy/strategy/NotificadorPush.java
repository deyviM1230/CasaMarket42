package com.example.demostrategy.strategy;

import com.example.demostrategy.model.DatosNotificacion;


public class NotificadorPush implements INotificador {
    private String servicioPush;
    private String idDispositivo;

    public NotificadorPush(String servicioPush, String idDispositivo) {
        this.servicioPush = servicioPush;
        this.idDispositivo = idDispositivo;
    }

    @Override
    public void enviar(DatosNotificacion data) {
        System.out.println("Enviando Notificación Push al dispositivo " + idDispositivo);
        System.out.println("Mensaje: " + data.getMensaje());
    }
}
