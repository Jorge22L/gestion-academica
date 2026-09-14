package com.uam.gestionacademica.aplicacion.servicio;

import com.uam.gestionacademica.aplicacion.excepcion.CorreoYaRegistradoException;
import com.uam.gestionacademica.aplicacion.excepcion.EstudianteNoEncontradoException;
import com.uam.gestionacademica.aplicacion.puerto.salida.PuertoRepositorioEstudiante;
import com.uam.gestionacademica.dominio.modelo.Estudiante;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioEstudiante {

    private final PuertoRepositorioEstudiante puertoRepositorioEstudiante;

    public ServicioEstudiante(PuertoRepositorioEstudiante puertoRepositorioEstudiante){
        this.puertoRepositorioEstudiante = puertoRepositorioEstudiante;
    }

    /*
    * Caso de uso: Crear estudiante
    */
    @Transactional
    public Estudiante crearEstudiante(Estudiante estudiante){
        estudiante.validarPuedeRegistrarse();
        validarCorreoParaCrear(estudiante.getCorreo());

        return puertoRepositorioEstudiante.guardar(estudiante);
    }

    /*
    * Caso de uso: Obtener estudiante por ID
    * */
    @Transactional(readOnly = true)
    public Estudiante obtenerEstudiantePorId(Long id){
        return puertoRepositorioEstudiante.buscarPorId(id)
                .orElseThrow(() -> new EstudianteNoEncontradoException(id));
    }

    /*
    * Caso de uso: Listar todos los estudiantes
    * */
    @Transactional(readOnly = true)
    public List<Estudiante> listarEstudiantes(){
        return puertoRepositorioEstudiante.listarTodos();
    }

    /*
    * Caso de uso: Actualizar estudiante existente
    * */
    @Transactional
    public Estudiante actualizarEstudiante(Long id, Estudiante datosEstudiante){
        if(!puertoRepositorioEstudiante.existePorId(id)){
            throw new EstudianteNoEncontradoException(id);
        }

        datosEstudiante.validarPuedeRegistrarse();
        validarCorreoParaActualizar(datosEstudiante.getCorreo(), id);
        Estudiante estudianteActualizado = new Estudiante(
                id,
                datosEstudiante.getNombre(),
                datosEstudiante.getCorreo(),
                datosEstudiante.getEdad()
        );

        return puertoRepositorioEstudiante.guardar(estudianteActualizado);
    }

    /*
    * Caso de uso: Eliminar estudiante
    * */
    @Transactional
    public void eliminarEstudiante(Long id){
        if(!puertoRepositorioEstudiante.existePorId(id)){
            throw new EstudianteNoEncontradoException(id);
        }

        puertoRepositorioEstudiante.eliminarPorId(id);
    }

    /*
    * Valida que el correo no esté registrado al crear un estudiante
    * */
    private void validarCorreoParaCrear(String correo){
        if(puertoRepositorioEstudiante.existePorCorreo(correo)){
            throw new CorreoYaRegistradoException(correo);
        }
    }

    /*
    * Valida que el correo no pertenezca a otro estudiante al actualizar
    * */
    private void validarCorreoParaActualizar(String correo, Long id){
        if(puertoRepositorioEstudiante.existePorCorreoyIdDistinto(correo, id)){
            throw new CorreoYaRegistradoException(correo);
        }
    }
}
