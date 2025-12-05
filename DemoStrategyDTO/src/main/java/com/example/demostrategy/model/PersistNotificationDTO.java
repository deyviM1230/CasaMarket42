package com.example.demostrategy.model;

import java.util.Date;

public class PersistNotificationDTO {
    private String channel;
    private String destination;
    private String content;
    private String status;
    private Date createdAt;
    private TemplateKey templateKey;

    public PersistNotificationDTO(String channel, String destination, String content, TemplateKey templateKey) {
        this.channel = channel;
        this.destination = destination;
        this.content = content;
        this.templateKey = this.templateKey;
        this.status = "PENDING";
        this.createdAt = new Date();
    }

    public String getChannel() { return channel; }
    public String getDestination() { return destination; }
    public String getContent() { return content; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getCreatedAt() { return createdAt; }
    public TemplateKey getTemplateKey() {
        return templateKey;
    }
    @Override
    public String toString() {
        return "PersistNotificationDTO{" +
                "channel='" + channel + '\'' +
                ", destination='" + destination + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

}
