package com.example.demostrategy.strategy;

import com.example.demostrategy.model.RenderedContentDTO;

public class NotificadorEmail implements INotificador{
    private String smtp;

    public NotificadorEmail(String smtp) {
        this.smtp = smtp;
    }

    @Override
    public void send(RenderedContentDTO content) {
        System.out.println("[EMAIL] Enviando a " + content.getDestination());
        System.out.println("Contenido:\n" + content.getContent());
    }
}
