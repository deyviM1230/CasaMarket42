package com.example.demostrategy.engine;

import com.example.demostrategy.model.Evento;
import com.example.demostrategy.strategy.INotificador;

public class NotificationEngine {

    private INotificador strategy;

    public void setStrategy(INotificador strategy) {
        this.strategy = strategy;
    }

    public void procesarEvento(Evento evento) {

        if (strategy == null) {
            System.out.println("No hay estrategia configurada!");
            return;
        }

        System.out.println("Procesando evento: " + evento.getTipo());
        strategy.enviar(evento.getData());
    }
}
