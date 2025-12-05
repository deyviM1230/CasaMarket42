package com.example.demostrategy.strategy;

import com.example.demostrategy.model.RenderedContentDTO;

public interface INotificador {
    void send(RenderedContentDTO data);
}
