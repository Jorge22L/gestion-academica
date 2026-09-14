package com.uam.gestionacademica.infraestructura.web;

import com.uam.gestionacademica.aplicacion.servicio.ServicioEstudiante;
import com.uam.gestionacademica.dominio.modelo.Estudiante;
import com.uam.gestionacademica.infraestructura.web.dto.RespuestaEstudiante;
import com.uam.gestionacademica.infraestructura.web.dto.SolicitudActualizacionEstudiante;
import com.uam.gestionacademica.infraestructura.web.dto.SolicitudEstudiante;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Controlador REST
* Responsabilidades
* 1. Recibir HTTP
* 2. Validar el request (@Valid)
* 3. Convertir DTO de entrada a objeto de dominio
* 4. Llamar al caso de uso
* 5. Transformar el resultado a DTO de respuesta
* 6. Devolver código HTTP correcto
* */
@RestController
@RequestMapping("/api/v1/estudiantes")
public class ControladorEstudiante {
    private final ServicioEstudiante servicioEstudiante;

    public ControladorEstudiante(ServicioEstudiante servicioEstudiante) {
        this.servicioEstudiante = servicioEstudiante;
    }

    @PostMapping
    public ResponseEntity<RespuestaEstudiante> crear(@Valid @RequestBody SolicitudEstudiante solicitudEstudiante){
        Estudiante estudiante = new Estudiante(
                null,
                solicitudEstudiante.nombre(),
                solicitudEstudiante.correo(),
                solicitudEstudiante.edad()
        );

        Estudiante estudianteCreado = servicioEstudiante.crearEstudiante(estudiante);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(aRespuesta(estudianteCreado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaEstudiante> obtenerPorId(@PathVariable Long id){
        Estudiante estudiante = servicioEstudiante.obtenerEstudiantePorId(id);
        return ResponseEntity.ok(aRespuesta(estudiante));
    }

    @GetMapping
    public ResponseEntity<List<RespuestaEstudiante>> listar(){
        List<RespuestaEstudiante> estudiantes = servicioEstudiante.listarEstudiantes()
                .stream()
                .map(this::aRespuesta)
                .toList();

        return ResponseEntity.ok(estudiantes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaEstudiante> actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudActualizacionEstudiante solicitud) {
        Estudiante estudiante = new Estudiante(
                id,
                solicitud.nombre(),
                solicitud.correo(),
                solicitud.edad()
        );

        Estudiante estudianteActualizado = servicioEstudiante.actualizarEstudiante(id, estudiante);

        return ResponseEntity.ok(aRespuesta(estudianteActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        servicioEstudiante.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }

    /*
    * Conversión manual de Dominio a DTO de respuesta.
    *
    * */
    private RespuestaEstudiante aRespuesta(Estudiante estudiante){
        return new RespuestaEstudiante(
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getCorreo(),
                estudiante.getEdad()
        );
    }
}
