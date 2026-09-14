package com.uam.gestionacademica.aplicacion.excepcion;

public class EstudianteNoEncontradoException extends RuntimeException{
    public EstudianteNoEncontradoException(Long id){
        super("Estudiante con id " + id + " no encontrado");
    }
}
