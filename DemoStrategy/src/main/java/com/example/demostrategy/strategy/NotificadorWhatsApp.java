package com.example.demostrategy.strategy;

import com.example.demostrategy.model.DatosNotificacion;


public class NotificadorWhatsApp implements INotificador{
    private String apiWhatsApp;
    private String numeroBodeguero;

    public NotificadorWhatsApp(String apiWhatsApp, String numeroBodeguero) {
        this.apiWhatsApp = apiWhatsApp;
        this.numeroBodeguero = numeroBodeguero;
    }

    @Override
    public void enviar(DatosNotificacion data) {
        System.out.println("Enviando WhatsApp a " + numeroBodeguero);
        System.out.println("Mensaje: " + data.getMensaje());
    }
}
