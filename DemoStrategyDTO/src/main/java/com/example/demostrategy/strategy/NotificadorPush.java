package com.example.demostrategy.strategy;


import com.example.demostrategy.model.RenderedContentDTO;

public class NotificadorPush implements INotificador {
    @Override
    public void send(RenderedContentDTO content) {
        System.out.println("[PUSH] Notificación enviada al token: " + content.getDestination());
        System.out.println("Contenido: " + content.getContent());
    }
}
