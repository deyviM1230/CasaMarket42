package com.example.demostrategy.model;

import java.util.Map;

public class NotificationEventDTO {
    private TemplateKey templateKey;
    private String channel;
    private String destination;
    private Map<String, Object> data;

    public NotificationEventDTO(TemplateKey templateKey, String channel, String destination, Map<String, Object> data) {
        this.templateKey = templateKey;
        this.channel = channel;
        this.destination = destination;
        this.data = data;
    }

    public TemplateKey getTemplateKey() { return templateKey; }
    public String getChannel() { return channel; }
    public String getDestination() { return destination; }
    public Map<String, Object> getData() { return data; }
    @Override
    public String toString() {
        return "NotificationEventDTO{" +
                "templateKey=" + templateKey +
                ", channel='" + channel + '\'' +
                ", destination='" + destination + '\'' +
                ", data=" + data +
                '}';
    }
}
