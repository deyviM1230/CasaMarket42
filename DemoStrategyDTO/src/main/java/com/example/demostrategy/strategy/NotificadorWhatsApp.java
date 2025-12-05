package com.example.demostrategy.strategy;


import com.example.demostrategy.model.RenderedContentDTO;

public class NotificadorWhatsApp implements INotificador{
    private String apiKey;

    public NotificadorWhatsApp(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void send(RenderedContentDTO content) {
        System.out.println("[WHATSAPP] Enviando a " + content.getDestination());
        System.out.println("Mensaje: " + content.getContent());
    }
}
