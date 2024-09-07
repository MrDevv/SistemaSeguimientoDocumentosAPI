package com.mrdevv.payload.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;
import com.mrdevv.payload.dto.rol.ResponseRolDTO;

import java.io.Serializable;

public record ResponseUsuarioDTO(
        @JsonProperty(value = "usuario_id")
        Long id,
        @JsonProperty(value = "user")
        String nombreUsuario,
        @JsonProperty(value = "persona")
        ResponsePersonaDTO personaDTO,
        @JsonProperty(value = "rol")
        ResponseRolDTO rolDTO
) implements Serializable {
}
