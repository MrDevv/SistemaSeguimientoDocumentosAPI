package com.mrdevv.payload.dto.usuario_area;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrdevv.payload.dto.usuario.CreateUsuarioDTO;

public record CreateUsuarioAreaDTO(
        @JsonProperty(value = "area_id")
        Long areaId,
        @JsonProperty(value = "usuario")
        CreateUsuarioDTO usuarioDTO
) {
}
