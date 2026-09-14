package com.uam.gestionacademica.infraestructura.persistencia.repositorio;

import com.uam.gestionacademica.infraestructura.persistencia.entidad.EntidadEstudianteJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEstudianteJpa extends JpaRepository<EntidadEstudianteJpa, Long> {
    boolean existsByCorreo(String correo);
    boolean existsByCorreoAndIdNot(String correo, Long id);
}
