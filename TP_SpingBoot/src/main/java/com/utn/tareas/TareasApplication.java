package com.utn.tareas;

import com.utn.tareas.model.Prioridad;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    private final TareaService tareaService;
    private final MensajeService mensajeService;

    public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Saludo inicial
        mensajeService.mostrarBienvenida();

        // 2. Presentar la configuración
        tareaService.imprimirConfiguracion();

        // 3. Listar las tareas cargadas al inicio
        System.out.println("\n--- Listado de Tareas Cargadas al Inicio ---");
        tareaService.findAll().forEach(System.out::println);

        // 4. Registrar una nueva actividad
        System.out.println("\n--- Registrando nueva actividad ---");
        try {
            tareaService.addTarea("Revisar TP de DDS", Prioridad.MEDIA);
            System.out.println("La tarea ha sido creada exitosamente.");
        } catch (IllegalStateException e) {
            System.out.println("No se pudo registrar la tarea. Motivo: " + e.getMessage());
        }


        // 5. Consultar actividades por realizar
        System.out.println("\n--- Actividades por Realizar ---");
        tareaService.findPendientes().forEach(System.out::println);

        // 6. Actualizar estado a 'Completada' (Tarea 1)
        System.out.println("\n--- Actualizando estado a 'Completada' (Tarea 1) ---");
        tareaService.marcarCompletada(1L);

        // 7. Mostrar resumen de actividad
        System.out.println("\n--- Resumen de Actividad ---");
        System.out.println(tareaService.getEstadisticas());

        // 8. Consultar listado de tareas concluidas
        System.out.println("\n--- Listado de Tareas Concluidas ---");
        tareaService.findCompletadas().forEach(System.out::println);

        // 9. Mensaje de cierre
        mensajeService.mostrarDespedida();
    }
}