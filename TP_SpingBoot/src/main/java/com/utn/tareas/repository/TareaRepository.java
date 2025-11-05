package com.utn.tareas.repository;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TareaRepository {

    private final List<Tarea> tareas = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong();

    public TareaRepository() {
        // Tareas de ejemplo
        this.save(new Tarea(null, "Estudiar Spring Boot", false, Prioridad.ALTA));
        this.save(new Tarea(null, "Hacer el TP de Fundamentos", false, Prioridad.ALTA));
        this.save(new Tarea(null, "Comprar pan", true, Prioridad.MEDIA));
        this.save(new Tarea(null, "Pasear al perro", false, Prioridad.BAJA));
    }

    public List<Tarea> findAll() {
        return tareas;
    }

    public Optional<Tarea> findById(Long id) {
        return tareas.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public Tarea save(Tarea tarea) {
        if (tarea.getId() == null) {
            tarea.setId(contador.incrementAndGet());
        }
        tareas.removeIf(t -> t.getId().equals(tarea.getId()));
        tareas.add(tarea);
        return tarea;
    }

    public void deleteById(Long id) {
        tareas.removeIf(t -> t.getId().equals(id));
    }
}
