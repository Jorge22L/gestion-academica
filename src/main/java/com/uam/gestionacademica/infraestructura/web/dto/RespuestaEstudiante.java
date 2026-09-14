package com.uam.gestionacademica.infraestructura.web.dto;

/*
* DTO de salida
* Simple, inmutable y sin lógica
* Es la representación JSON que se envía al cliente
* */
public record RespuestaEstudiante(
        Long id,
        String nombre,
        String correo,
        Integer edad
) {
}
