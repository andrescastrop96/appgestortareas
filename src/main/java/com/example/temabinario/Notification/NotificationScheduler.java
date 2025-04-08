package com.example.temabinario.notification;

import com.example.temabinario.model.Tarea;
import com.example.temabinario.repository.TareaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationScheduler {

    private final TareaRepository tareaRepository;
    private final NotificationService notificationService;

    public NotificationScheduler(TareaRepository tareaRepository, NotificationService notificationService) {
        this.tareaRepository = tareaRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 60000) // cada 60 segundos
    public void revisarTareas() {
        List<Tarea> tareas = tareaRepository.findAll();
        LocalDateTime ahora = LocalDateTime.now();

        for (Tarea tarea : tareas) {
            if (tarea.getFechaLimite() != null) {
                // Notificación si faltan menos de 24 horas
                if (tarea.getFechaLimite().minusHours(24).isBefore(ahora)
                        && tarea.getFechaLimite().isAfter(ahora)) {
                    notificationService.enviarNotificacion(
                            new TareaReminder(tarea, NotificationType.DEADLINE_SOON, ahora)
                    );
                }

                // Notificación si la prioridad es alta (esto depende de cómo lo estés manejando)
                if ("ALTA".equalsIgnoreCase(tarea.getPrioridad())) {
                    notificationService.enviarNotificacion(
                            new TareaReminder(tarea, NotificationType.URGENT_TASK, ahora)
                    );
                }
            }
        }
    }
}
