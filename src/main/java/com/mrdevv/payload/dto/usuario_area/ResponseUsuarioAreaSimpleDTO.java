package com.mrdevv.payload.dto.usuario_area;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioSimpleDTO;

import java.util.List;

public record ResponseUsuarioAreaSimpleDTO(
        String area,
        @JsonProperty(value = "usuarios_area")
        List<ResponseUsuarioSimpleDTO> usuarioSimpleDTO
) {
}
