package com.example.temabinario.controller;

import com.example.temabinario.model.Tarea;
import com.example.temabinario.repository.TareaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*") // Permite acceso desde el HTML
public class TareaController {

    private final TareaRepository tareaRepository;

    public TareaController(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @GetMapping
    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada) {
        return tareaRepository.findById(id).map(tarea -> {
            tarea.setTexto(tareaActualizada.getTexto());
            tarea.setCompletada(tareaActualizada.isCompletada());
            return tareaRepository.save(tarea);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareaRepository.deleteById(id);
    }
}
