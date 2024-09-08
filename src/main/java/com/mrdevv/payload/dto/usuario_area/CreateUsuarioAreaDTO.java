package com.mrdevv.payload.dto.usuario_area;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrdevv.payload.dto.usuario.CreateUsuarioDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CreateUsuarioAreaDTO(
        @NotNull(message = "El id de area es requerida")
        @JsonProperty(value = "area_id")
        Long areaId,
        @NotNull
        @Valid
        @JsonProperty(value = "usuario")
        CreateUsuarioDTO usuarioDTO
) {
}
