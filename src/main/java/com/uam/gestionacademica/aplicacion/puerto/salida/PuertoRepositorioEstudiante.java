package com.uam.gestionacademica.aplicacion.puerto.salida;

import com.uam.gestionacademica.dominio.modelo.Estudiante;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PuertoRepositorioEstudiante {
    Estudiante guardar(Estudiante estudiante);
    Optional<Estudiante> buscarPorId(Long id);
    List<Estudiante> listarTodos();
    void eliminarPorId(Long id);
    boolean existePorId(Long id);
    boolean existePorCorreo(String correo);
    boolean existePorCorreoyIdDistinto(String correo, Long id);
}
