package com.example.demostrategy.strategy;

import com.example.demostrategy.model.DatosNotificacion;

public class NotificadorEmail implements INotificador{
    private String servidorSMTP;
    private String emailReceptor;

    public NotificadorEmail(String servidorSMTP, String emailReceptor) {
        this.servidorSMTP = servidorSMTP;
        this.emailReceptor = emailReceptor;
    }

    @Override
    public void enviar(DatosNotificacion data) {
        System.out.println("Enviando Email a " + emailReceptor);
        System.out.println("Asunto: Notificación de CasaMarket");
        System.out.println("Mensaje: " + data.getMensaje());
    }
}
