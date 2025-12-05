package com.example.demostrategy.template;

import com.example.demostrategy.model.NotificationEventDTO;
import com.example.demostrategy.model.RenderedContentDTO;

public class TemplateService {
    private TemplateRepository repository;

    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public RenderedContentDTO render(NotificationEventDTO event) {

        String template = repository.getTemplate(event.getTemplateKey());
        String content = template;

        // Reemplazar variables del mapa data
        for (String key : event.getData().keySet()) {
            content = content.replace("{" + key + "}", event.getData().get(key).toString());
        }

        return new RenderedContentDTO(
                event.getChannel(),
                event.getDestination(),
                content
        );
    }
}
