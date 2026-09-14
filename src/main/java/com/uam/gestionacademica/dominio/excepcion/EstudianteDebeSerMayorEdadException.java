package com.uam.gestionacademica.dominio.excepcion;

public class EstudianteDebeSerMayorEdadException extends RuntimeException{
    public EstudianteDebeSerMayorEdadException(String mensaje){
        super(mensaje);
    }
}
