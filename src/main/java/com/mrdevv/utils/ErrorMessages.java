package com.mrdevv.utils;

public enum ErrorMessages {
    PERSONA_DUPLICATE_BACKEND("Entrada duplicada '%s' para la llave mae_persona.DNI"),
    PERSONA_DUPLICATE_FRONT("La persona con DNI '%s' ya se encuentra registrada."),
    AREA_DUPLICATE_BACKEND("Entrada duplicada '%s' para la llave mae_area.DESCRIPCION"),
    AREA_DUPLICATE_FRONT("El área '%s' ya se encuentra registrada."),
    USUARIO_DUPLICATE_BACKEND("Entrada duplicada '%s' para la llave mae_usuario.NOMBRE_USUARIO"),
    USUARIO_DUPLICATE_FRONT("La el usuario '%s' ya se encuentra registrado."),
    AREA_NOT_FOUND_BACKEND("El área con id '%s' no se encontró en la base de datos."),
    AREA_NOT_FOUND_FRONT("No se encontró el área."),
    ROL_NOT_FOUND_BACKEND("El rol con el id '%s' no se encontró en la base de datos."),
    ROL_NOT_FOUND_FRONT("No se encontró el rol.");

    private final String mensaje;

    ErrorMessages(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMessage(Object... args){
        return String.format(this.mensaje, args);
    }

}
