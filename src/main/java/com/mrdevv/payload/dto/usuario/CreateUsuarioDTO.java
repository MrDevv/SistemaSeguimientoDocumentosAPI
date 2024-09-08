package com.mrdevv.payload.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrdevv.payload.dto.persona.CreatePersonaDTO;

public record CreateUsuarioDTO(
        @JsonProperty(value = "rol_id")
        Long rolId,
        @JsonProperty(value = "persona")
        CreatePersonaDTO personaDTO,
        @JsonProperty(value = "nombre_usuario")
        String nombreUsuario,
        String password
) {
}
