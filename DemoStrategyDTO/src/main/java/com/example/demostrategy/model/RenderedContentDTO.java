package com.example.demostrategy.model;

import java.util.Map;

public class RenderedContentDTO {

    private String channel;
    private String destination;
    private String content;

    public RenderedContentDTO(String channel, String destination, String content) {
        this.channel = channel;
        this.destination = destination;
        this.content = content;
    }

    public String getChannel() { return channel; }
    public String getDestination() { return destination; }
    public String getContent() { return content; }

    @Override
    public String toString() {
        return "RenderedContentDTO{" +
                "channel='" + channel + '\'' +
                ", destination='" + destination + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
