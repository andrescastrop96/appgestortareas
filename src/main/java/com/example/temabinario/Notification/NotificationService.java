package com.example.temabinario.notification;

import org.springframework.stereotype.Service;
import com.example.temabinario.model.Tarea;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    public void enviarNotificaciones(List<Tarea> tareas) {
        for (Tarea tarea : tareas) {
            if (!tarea.isCompletada() && tarea.getFechaLimite() != null) {
                Duration duracion = Duration.between(LocalDateTime.now(), tarea.getFechaLimite());
                if (duracion.toHours() <= 24 && !duracion.isNegative()) {
                    System.out.println("🔔 ¡Tarea urgente! \"" + tarea.getTexto() + "\" vence en menos de 24 horas.");
                    
                    if (tarea.getPrioridad() != null) {
                        System.out.println("Prioridad: " + tarea.getPrioridad());
                    }
                }
            }
        }
    }
    
    public void enviarNotificacion(TareaReminder reminder) {
        Tarea tarea = reminder.getTarea();
        NotificationType tipo = reminder.getType();
        
        switch (tipo) {
            case DEADLINE_SOON:
                System.out.println("🔔 Tarea \"" + tarea.getTexto() + "\" vence pronto (en menos de 24 horas).");
                break;
            case URGENT_TASK:
                System.out.println("⚠️ Tarea urgente: \"" + tarea.getTexto() + "\" con prioridad ALTA.");
                break;
        }
    }
}
