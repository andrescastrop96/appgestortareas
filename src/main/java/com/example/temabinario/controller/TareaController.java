package com.example.temabinario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.temabinario.model.Tarea;
import com.example.temabinario.repository.TareaRepository;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*") // Permite acceso desde el HTML
public class TareaController {

    private final TareaRepository tareaRepository;

    public TareaController(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @GetMapping("/")
    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    @GetMapping("/")
    public String index() {
        return "¡Hola desde Railway!";
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
