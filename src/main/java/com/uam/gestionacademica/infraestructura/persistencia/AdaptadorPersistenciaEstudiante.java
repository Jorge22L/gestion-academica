package com.uam.gestionacademica.infraestructura.persistencia;

/*
* Adaptador de persistencia
* Implementa el puerto definido en la capa de aplicación.
*
* Este es el único lugar donde se conectan:
* - Spring Data JPA
* - PostgreSQL
*
* El servicio de aplicación solo conoce la interfaz
*
* */

import com.uam.gestionacademica.aplicacion.puerto.salida.PuertoRepositorioEstudiante;
import com.uam.gestionacademica.dominio.modelo.Estudiante;
import com.uam.gestionacademica.infraestructura.persistencia.entidad.EntidadEstudianteJpa;
import com.uam.gestionacademica.infraestructura.persistencia.mapeador.MapeadorEstudiante;
import com.uam.gestionacademica.infraestructura.persistencia.repositorio.RepositorioEstudianteJpa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdaptadorPersistenciaEstudiante implements PuertoRepositorioEstudiante {

    private final RepositorioEstudianteJpa repositorioEstudianteJpa;
    private final MapeadorEstudiante mapeadorEstudiante;

    public AdaptadorPersistenciaEstudiante(RepositorioEstudianteJpa repositorioEstudianteJpa,
                                                    MapeadorEstudiante mapeadorEstudiante) {
        this.repositorioEstudianteJpa = repositorioEstudianteJpa;
        this.mapeadorEstudiante = mapeadorEstudiante;
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        EntidadEstudianteJpa entidad = mapeadorEstudiante.aEntidad(estudiante);
        EntidadEstudianteJpa entidadGuardada = repositorioEstudianteJpa.save(entidad);
        return mapeadorEstudiante.aDominio(entidadGuardada);
    }

    @Override
    public Optional<Estudiante> buscarPorId(Long id) {
        return repositorioEstudianteJpa.findById(id)
                .map(mapeadorEstudiante::aDominio);
    }

    @Override
    public List<Estudiante> listarTodos() {
        return repositorioEstudianteJpa.findAll()
                .stream()
                .map(mapeadorEstudiante::aDominio)
                .toList();
    }

    @Override
    public void eliminarPorId(Long id) {
        repositorioEstudianteJpa.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return repositorioEstudianteJpa.existsById(id);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return repositorioEstudianteJpa.existsByCorreo(correo);
    }

    @Override
    public boolean existePorCorreoyIdDistinto(String correo, Long id) {
        return repositorioEstudianteJpa.existsByCorreoAndIdNot(correo, id);
    }
}
