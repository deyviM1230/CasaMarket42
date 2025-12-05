package com.example.demostrategy.engine;

import com.example.demostrategy.model.NotificationEventDTO;
import com.example.demostrategy.model.PersistNotificationDTO;
import com.example.demostrategy.model.RenderedContentDTO;
import com.example.demostrategy.repository.NotificationRepository;
import com.example.demostrategy.strategy.INotificador;
import com.example.demostrategy.template.TemplateService;

import java.util.Map;

public class NotificationEngine {

    private TemplateService templateService;
    private NotificationRepository repository;
    private Map<String, INotificador> estrategias;

    public NotificationEngine(TemplateService templateService,
                              NotificationRepository repository,
                              Map<String, INotificador> estrategias) {
        this.templateService = templateService;
        this.repository = repository;
        this.estrategias = estrategias;
    }

    public void process(NotificationEventDTO event) {

        System.out.println("\n===============================");
        System.out.println("DTO 1 → NotificationEventDTO recibido:");
        System.out.println(event);
        System.out.println("===============================");

        // 1. Renderizar plantilla
        RenderedContentDTO rendered = templateService.render(event);

        System.out.println("\n===============================");
        System.out.println("DTO 2 → RenderedContentDTO generado:");
        System.out.println(rendered);
        System.out.println("===============================");

        // 2. Guardar en repositorio
        PersistNotificationDTO persist = new PersistNotificationDTO(
                rendered.getChannel(),
                rendered.getDestination(),
                rendered.getContent(),
                event.getTemplateKey()
        );
        System.out.println("\n===============================");
        System.out.println("DTO 3 → PersistNotificationDTO (ANTES de guardar):");
        System.out.println(persist);
        System.out.println("===============================");

        repository.save(persist);

        // 3. Randomizar éxito o falla
        int resultado = (int)(Math.random() * 2) + 1; // 1 o 2

        if (resultado == 2) {
            System.out.println("El envío ha fallado, no se enviará el mensaje.");
            persist.setStatus("CANCELED");
            repository.update(persist);

            System.out.println("\n===============================");
            System.out.println("DTO 3 → PersistNotificationDTO (ENVÍO CANCELADO):");
            System.out.println(persist);
            System.out.println("===============================");

            return;  // Se corta el proceso aquí
        }

        // 4. ENVÍO EXITOSO — Se ejecuta estrategia
        INotificador notificador = estrategias.get(rendered.getChannel());
        notificador.send(rendered);

        persist.setStatus("SENT");
        repository.update(persist);

        System.out.println("\n===============================");
        System.out.println("DTO 3 → PersistNotificationDTO (ENVÍO EXITOSO):");
        System.out.println(persist);
        System.out.println("===============================");

        System.out.println("✅ Notificación enviada correctamente.\n");

        // 5. Actualizar estado
        persist.setStatus("SENT");
        repository.update(persist);

        System.out.println("\n===============================");
        System.out.println("DTO 3 → PersistNotificationDTO (DESPUÉS del envío):");
        System.out.println(persist);
        System.out.println("===============================");

        System.out.println("✅ [ENGINE] Notificación enviada correctamente.\n");
    }
}
