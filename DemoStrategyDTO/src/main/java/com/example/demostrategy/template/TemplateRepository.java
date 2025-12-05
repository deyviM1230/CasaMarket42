package com.example.demostrategy.template;

import com.example.demostrategy.model.TemplateKey;

import java.util.HashMap;
import java.util.Map;

public class TemplateRepository {
    private Map<TemplateKey, String> templates = new HashMap<>();

    public TemplateRepository() {
        templates.put(TemplateKey.NUEVO_PEDIDO,
                "Hola {nombreCliente}, tu pedido {idPedido} ha sido registrado.");

        templates.put(TemplateKey.STOCK_MINIMO,
                "Alerta: el producto {producto} está por debajo del stock mínimo.");
    }

    public String getTemplate(TemplateKey key) {
        return templates.get(key);
    }
}
