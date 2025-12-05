package com.example.demostrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.example.demostrategy.engine.NotificationEngine;
import com.example.demostrategy.model.NotificationEventDTO;
import com.example.demostrategy.model.PersistNotificationDTO;
import com.example.demostrategy.model.TemplateKey;
import com.example.demostrategy.repository.NotificationRepository;
import com.example.demostrategy.strategy.*;
import com.example.demostrategy.template.TemplateRepository;
import com.example.demostrategy.template.TemplateService;

public class Launcher {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ==========================
        // 1. MEDIADOR ENTRE FRONTED Y BACKEND
        // ==========================

        TemplateRepository templateRepo = new TemplateRepository();
        TemplateService templateService = new TemplateService(templateRepo);
        NotificationRepository notificationRepository = new NotificationRepository();

        Map<String, INotificador> estrategias = new HashMap<>();
        estrategias.put("whatsapp", new NotificadorWhatsApp("API-1234"));
        estrategias.put("email", new NotificadorEmail("smtp.gmail.com"));
        estrategias.put("push", new NotificadorPush());

        NotificationEngine engine = new NotificationEngine(
                templateService,
                notificationRepository,
                estrategias
        );

        // ==========================
        // 2. INICIO DEL FRONTED
        // ==========================

        while (true) {
            System.out.println("\n=== SISTEMA DE NOTIFICACIONES ===");
            System.out.println("1. Enviar notificación");
            System.out.println("2. Ver historial");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1:
                    enviarNotificacion(engine, sc);
                    break;

                case 2:
                    mostrarHistorial(notificationRepository);
                    break;

                case 3:
                    System.out.println("saliendo del sistema...");
                    return;

                default:
                    System.out.println("Opción inválida. Intente otra vez.");
            }
        }
    }

    // ==========================
    // MÉTODO PARA ENVIAR NOTIFICACIÓN
    // ==========================

    private static void enviarNotificacion(NotificationEngine engine, Scanner sc) {

        System.out.println("\n=== FRONTEND SIMULADOR ===");
        System.out.println("1. NUEVO_PEDIDO");
        System.out.println("2. STOCK_MINIMO");
        System.out.print("Seleccione la plantilla: ");

        int plantilla = Integer.parseInt(sc.nextLine());
        TemplateKey key;
        if (plantilla == 1) {
            key = TemplateKey.NUEVO_PEDIDO;
        } else {
            key = TemplateKey.STOCK_MINIMO;
        }

        System.out.print("Canal (whatsapp/email/push): ");
        String canal = sc.nextLine().toLowerCase();

        System.out.print("Destino (número, correo o token): ");
        String destino = sc.nextLine();

        Map<String, Object> data = new HashMap<>();

        if (key == TemplateKey.NUEVO_PEDIDO) {
            System.out.print("Nombre del cliente: ");
            data.put("nombreCliente", sc.nextLine());

            System.out.print("ID del pedido: ");
            data.put("idPedido", sc.nextLine());
        } else {
            System.out.print("Nombre del producto: ");
            data.put("producto", sc.nextLine());
        }

        NotificationEventDTO event = new NotificationEventDTO(
                key,
                canal,
                destino,
                data
        );

        engine.process(event);
    }

    // ==========================
    // MÉTODO PARA MOSTRAR HISTORIAL
    // ==========================

    private static void mostrarHistorial(NotificationRepository repo) {

        System.out.println("\n=== HISTORIAL DE NOTIFICACIONES ===");

        List<PersistNotificationDTO> lista = repo.findAll();

        if (lista.isEmpty()) {
            System.out.println("No hay notificaciones enviadas.");
            return;
        }

        int i = 1;
        for (PersistNotificationDTO n : lista) {
            System.out.println("\nNotificación #" + (i++));
            System.out.println("Canal: " + n.getChannel());
            System.out.println("Destino: " + n.getDestination());
            System.out.println("Contenido: " + n.getContent());
            System.out.println("Estado: " + n.getStatus());
            System.out.println("Fecha: " + n.getCreatedAt());
        }
    }
}
