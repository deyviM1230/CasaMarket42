package com.example.demostrategy.repository;

import com.example.demostrategy.model.PersistNotificationDTO;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {
    private List<PersistNotificationDTO> notifications = new ArrayList<>();

    public void save(PersistNotificationDTO dto) {
        notifications.add(dto);
        System.out.println("[REPOSITORY] Notificación guardada con estado: " + dto.getStatus());
    }

    public void update(PersistNotificationDTO dto) {
        System.out.println("[REPOSITORY] Notificación actualizada a estado: " + dto.getStatus());
    }

    public List<PersistNotificationDTO> findAll() {
        return notifications;
    }
}
