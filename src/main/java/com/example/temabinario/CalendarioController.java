package com.example.temabinario;

import java.time.LocalDate;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {

    static class Evento {
        public String titulo;
        public LocalDate fecha;

        public Evento(String titulo, LocalDate fecha) {
            this.titulo = titulo;
            this.fecha = fecha;
        }
    }

    private final List<Evento> eventos = new ArrayList<>(List.of(
        new Evento("Examen final de estructuras", LocalDate.of(2025, 4, 15)),
        new Evento("Entrega del proyecto", LocalDate.of(2025, 4, 20)),
        new Evento("Maratón Bogotá", LocalDate.of(2025, 12, 7))
    ));

    @GetMapping("/eventos")
    public List<Evento> obtenerEventos() {
        return eventos;
    }

    @PostMapping("/agregar")
    public Evento agregarEvento(@RequestBody Evento nuevo) {
        eventos.add(nuevo);
        return nuevo;
    }
}

