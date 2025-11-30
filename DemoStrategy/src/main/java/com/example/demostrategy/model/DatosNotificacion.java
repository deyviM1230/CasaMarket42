package com.example.demostrategy.model;
import java.util.Map;

public class DatosNotificacion {
    private String mensaje;
    private String receptorId;
    private String canalPreferido;
    private Map<String, Object> metadata;

    public DatosNotificacion(String mensaje, String receptorId, String canalPreferido, Map<String, Object> metadata) {
        this.mensaje = mensaje;
        this.receptorId = receptorId;
        this.canalPreferido = canalPreferido;
        this.metadata = metadata;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getReceptorId() {
        return receptorId;
    }

    public String getCanalPreferido() {
        return canalPreferido;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }
}
