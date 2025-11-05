package com.utn.tareas.service;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.model.Tarea;
import com.utn.tareas.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository repository;

    @Value("${app.nombre}")
    private String appNombre;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    public TareaService(TareaRepository repository) {
        this.repository = repository;
    }

    public Tarea addTarea(String descripcion, Prioridad prioridad) {
        if (repository.findAll().size() >= maxTareas) {
            throw new IllegalStateException("Se alcanzo el límite máximo de tareas.");
        }
        Tarea nuevaTarea = new Tarea(null, descripcion, false, prioridad);
        return repository.save(nuevaTarea);
    }

    public List<Tarea> findAll() {
        return repository.findAll();
    }

    public List<Tarea> findPendientes() {
        return repository.findAll().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    public List<Tarea> findCompletadas() {
        return repository.findAll().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    public Tarea marcarCompletada(Long id) {
        return repository.findById(id).map(tarea -> {
            tarea.setCompletada(true);
            return repository.save(tarea);
        }).orElse(null);
    }

    public String getEstadisticas() {
        if (!mostrarEstadisticas) {
            return "La visualización de estadísticas está desactivada.";
        }
        long total = repository.findAll().size();
        long completadas = findCompletadas().size();
        long pendientes = findPendientes().size();
        return String.format("Estadísticas: Total de tareas: %d, Completadas: %d, Pendientes: %d",
                total, completadas, pendientes);
    }

    public void imprimirConfiguracion() {
        System.out.println("--- Configuración de la Aplicación ---");
        System.out.println("Nombre: " + appNombre);
        System.out.println("Máximo de Tareas: " + maxTareas);
        System.out.println("Mostrar Estadísticas: " + mostrarEstadisticas);
        System.out.println("------------------------------------");
    }
}