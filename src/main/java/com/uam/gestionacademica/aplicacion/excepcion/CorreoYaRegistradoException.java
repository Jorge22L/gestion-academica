package com.uam.gestionacademica.aplicacion.excepcion;

public class CorreoYaRegistradoException extends RuntimeException {
    public CorreoYaRegistradoException(String correo) {
        super("El correo " + correo + " ya está registrado");

    }
}
