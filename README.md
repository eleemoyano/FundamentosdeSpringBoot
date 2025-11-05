# FundamentosdeSpringBoot
Empezamos creando el proyecto con Spring Initializr y armando la estructura profesional de paquetes (model, repository, service).

En el modelo, creamos la clase Tarea.

En el repositorio, hacemos una clase TareaRepository (anotada con @Repository ) que, por ahora, guarda las tareas en una lista en memoria.


En el servicio, creamos TareaService (con @Service ) donde va la lógica de negocio (agregar tarea, listar pendientes, ver estadísticas, etc.). Lo importante es que inyectamos el repositorio usando el constructor, que es una buena práctica.




Una parte clave del TP es la configuración. Usamos el archivo application.properties para definir variables, como la cantidad máxima de tareas , y las leemos desde el servicio usando la anotación @Value.



Finalmente, aprendemos a manejar distintos entornos usando Profiles. Creamos un application-dev.properties y un application-prod.properties para que la aplicación se comporte diferente si está en desarrollo o en producción (por ejemplo, cambiando el límite de tareas o los mensajes que muestra). Para probar todo, modificamos la clase principal para que implemente CommandLineRunner y ejecute una serie de pasos  que demuestran cómo funciona todo.
