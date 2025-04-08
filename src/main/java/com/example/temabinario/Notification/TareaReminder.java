package com.example.temabinario.notification;

import com.example.temabinario.model.Tarea;
import java.time.LocalDateTime;

public class TareaReminder {
    private Tarea tarea;
    private NotificationType type;
    private LocalDateTime triggerTime;

    public TareaReminder(Tarea tarea, NotificationType type, LocalDateTime triggerTime) {
        this.tarea = tarea;
        this.type = type;
        this.triggerTime = triggerTime;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public NotificationType getType() {
        return type;
    }

    public LocalDateTime getTriggerTime() {
        return triggerTime;
    }
    
    public String getNotificationMessage() {
        switch (type) {
            case DEADLINE_SOON:
                return "🔔 Tarea \"" + tarea.getTexto() + "\" vence pronto (en menos de 24 horas).";
            case URGENT_TASK:
                return "⚠️ Tarea urgente: \"" + tarea.getTexto() + "\" con prioridad " + tarea.getPrioridad() + ".";
            default:
                return "Notificación para tarea: \"" + tarea.getTexto() + "\"";
        }
    }
}
