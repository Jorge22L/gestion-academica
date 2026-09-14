package com.uam.gestionacademica.infraestructura.persistencia.mapeador;

import com.uam.gestionacademica.dominio.modelo.Estudiante;
import com.uam.gestionacademica.infraestructura.persistencia.entidad.EntidadEstudianteJpa;
import org.springframework.stereotype.Component;

@Component
public class MapeadorEstudiante {

    public EntidadEstudianteJpa aEntidad(Estudiante estudiante) {
        if(estudiante == null) {
            return null;
        }

        return EntidadEstudianteJpa.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .correo(estudiante.getCorreo())
                .edad(estudiante.getEdad())
                .build();
    }

    public Estudiante aDominio(EntidadEstudianteJpa entidad){
        if(entidad == null) {
            return null;
        }

        return new Estudiante(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getCorreo(),
                entidad.getEdad()
        );
    }
}
