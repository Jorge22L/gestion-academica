package com.uam.gestionacademica.dominio.modelo;

import com.uam.gestionacademica.dominio.excepcion.EstudianteDebeSerMayorEdadException;

public class Estudiante {
    private final Long id;
    private final String nombre;
    private final String correo;
    private final Integer edad;

    public Estudiante(Long id, String nombre, String correo, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public Integer getEdad() {
        return edad;
    }

    public boolean esMayorDeEdad(){
        return edad != null && edad >= 18;
    }

    /*
        Validación de negocio
        Esta regla pertenece al dominio
    * */
    public void validarPuedeRegistrarse(){
        if(!esMayorDeEdad()){
            throw new EstudianteDebeSerMayorEdadException("El estudiante debe ser mayor de edad para registrarse");

        }
    }
}
