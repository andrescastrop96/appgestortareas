package com.example.temabinario.controller;

import com.example.temabinario.notification.NotificationService;
import com.example.temabinario.repository.TareaRepository;
import com.example.temabinario.model.Tarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class VistaController {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private NotificationService notificationService;

    // Esto maneja la vista principal
    @RequestMapping("/")
    public String index() {
        return "index"; // Tu archivo index.html en templates/
    }

    // Esto se activa cuando se presiona el botón
    @PostMapping("/activar-alertas")
    public String activarAlertas() {
        List<Tarea> tareas = tareaRepository.findAll();
        notificationService.enviarNotificaciones(tareas);
        return "redirect:/?alertas=true";
    }
}
