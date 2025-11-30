package com.example.demostrategy;

import java.util.Map;
import java.util.Scanner;
import com.example.demostrategy.engine.NotificationEngine;
import com.example.demostrategy.model.DatosNotificacion;
import com.example.demostrategy.model.Evento;
import com.example.demostrategy.model.TipoEvento;
import com.example.demostrategy.strategy.*;

public class Launcher {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        NotificationEngine engine = new NotificationEngine();

        System.out.println("=== MOTOR DE NOTIFICACIONES (MODO PRUEBAS) ===");

        while (true) {

            System.out.println("\n===============================");
            System.out.println("Nueva prueba de notificación");
            System.out.println("===============================");

            // 1. Ingresar mensaje
            System.out.print("Mensaje: ");
            String mensaje = sc.nextLine();

            // 2. Ingresar receptor
            System.out.print("Receptor ID (ej: bodeguero01): ");
            String receptor = sc.nextLine();

            // 3. Seleccionar tipo de evento
            System.out.println("Tipo de evento:");
            System.out.println("1. NUEVO_PEDIDO");
            System.out.println("2. STOCK_MINIMO");
            System.out.println("3. DOCUMENTO_GENERADO");
            System.out.println("4. ALERTA_SISTEMA");
            System.out.print("Seleccione evento: ");
            int opcEvento = Integer.parseInt(sc.nextLine());

            TipoEvento tipoEvento = switch (opcEvento) {
                case 1 -> TipoEvento.NUEVO_PEDIDO;
                case 2 -> TipoEvento.STOCK_MINIMO;
                case 3 -> TipoEvento.DOCUMENTO_GENERADO;
                case 4 -> TipoEvento.ALERTA_SISTEMA;
                default -> TipoEvento.NUEVO_PEDIDO;
            };

            // 4. Seleccionar estrategia
            System.out.println("\nCanal de envío:");
            System.out.println("1. WhatsApp");
            System.out.println("2. Email");
            System.out.println("3. Push");
            System.out.print("Seleccione canal: ");
            int opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1 -> {
                    System.out.println("Ingrese el numero del cliente");
                    String numeroCliente = sc.nextLine();
                    engine.setStrategy(new NotificadorWhatsApp("API_KEY", numeroCliente));
                }
                case 2 ->{
                    System.out.println("Ingrese el correo del cliente");
                    String correoCliente = sc.nextLine();
                    engine.setStrategy(new NotificadorEmail("smtp.server.com", correoCliente));
                }
                case 3 ->{
                    System.out.println("Ingrese el Id del cliente");
                    String idCliente = sc.nextLine();
                    engine.setStrategy(new NotificadorPush("device123",idCliente));
                }
                default ->{
                    System.out.println("Canal incalido usando whapsap por defecto");
                    System.out.println("Ingrese el numero del cliente");
                    String numeroCliente = sc.nextLine();
                    engine.setStrategy(new NotificadorWhatsApp("API_KEY", numeroCliente));
                }
            }

            // 5. Crear datos de notificación
            DatosNotificacion data = new DatosNotificacion(
                    mensaje,
                    receptor,
                    "NA",
                    Map.of("prueba", "OK") // datos adicionales de ejemplo
            );

            // 6. Crear evento
            Evento evento = new Evento(tipoEvento, data);

            // 7. Procesar
            System.out.println("\n--- Enviando notificación ---");
            engine.procesarEvento(evento);

            // 8. Salir o continuar
            System.out.print("\n¿Deseas hacer otra prueba? (s/n): ");
            String continuar = sc.next();

            if (continuar.equalsIgnoreCase("n")) {
                System.out.println("Saliendo del motor de pruebas...");
                break;
            }
        }

        sc.close();
    }
}
