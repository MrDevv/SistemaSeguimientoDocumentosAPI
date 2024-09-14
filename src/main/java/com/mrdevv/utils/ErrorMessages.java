package com.mrdevv.utils;

public enum ErrorMessages {
    PERSONA_DUPLICATE_BACKEND("Entrada duplicada '%s' para la llave mae_persona.DNI"),
    PERSONA_DUPLICATE_FRONT("La persona con DNI '%s' ya se encuentra registrada."),
    AREA_DUPLICATE_BACKEND("Entrada duplicada '%s' para la llave mae_area.DESCRIPCION"),
    AREA_DUPLICATE_FRONT("El área '%s' ya se encuentra registrada."),
    USUARIO_DUPLICATE_BACKEND("Entrada duplicada '%s' para la llave mae_usuario.NOMBRE_USUARIO"),
    USUARIO_DUPLICATE_FRONT("La el usuario '%s' ya se encuentra registrado."),
    INDICACION_DUPLICATE_BACKEND("Entrada duplicada '%s' para la llave mae_indicacion.DESCRIPCION"),
    INDICACION_DUPLICATE_FRONT("La indicación '%s' ya se encuentra registrada."),
    AREA_NOT_FOUND_BACKEND("El área con id '%s' no se encontró en la base de datos."),
    AREA_NOT_FOUND_FRONT("No se encontró el área."),
    ROL_NOT_FOUND_BACKEND("El rol con el id '%s' no se encontró en la base de datos."),
    ROL_NOT_FOUND_FRONT("No se encontró el rol."),
    TIPO_DOCUMENTO_NOT_FOUND_FRONT("No se encontró el tipo documento."),
    TIPO_DOCUMENTO_NOT_FOUND_BACKEND("El tipo documento con el id '%s' no se encontró en la base de datos."),
    DOCUMENTO_NOT_FOUND_FRONT("No se encontró el documento."),
    DOCUMENTO_NOT_FOUND_BACKEND("El documento con el id '%s' no se encontró en la base de datos."),
    INDICACION_NOT_FOUND_FRONT("No se encontró la indicación."),
    INDICACION_NOT_FOUND_BACKEND("La indicación con el id '%s' no se encontró en la base de datos.");




    private final String mensaje;

    ErrorMessages(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMessage(Object... args){
        return String.format(this.mensaje, args);
    }

}
