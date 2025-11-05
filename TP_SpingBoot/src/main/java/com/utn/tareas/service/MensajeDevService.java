package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println("=====================================================");
        System.out.println("== BIENVENIDO AL GESTOR DE TAREAS (ENTORNO DE DESARROLLO) ==");
        System.out.println("==      Aquí puedes probar, depurar y experimentar.      ==");
        System.out.println("=====================================================");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println("=====================================================");
        System.out.println("==       CERRANDO GESTOR DE TAREAS (DESARROLLO)      ==");
        System.out.println("=====================================================");
    }
}
