package com.mrdevv.payload.dto.usuario_area;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrdevv.payload.dto.usuario.CreateUsuarioDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateUsuarioAreaDTO(
        @NotNull(message = "El id del area es requerida")
        @JsonProperty(value = "area_id")
        @Min(value = 1, message = "El are id debe ser un número positivo")
        Long areaId,
        @NotNull(message = "Los datos del usuario son requeridos")
        @Valid
        @JsonProperty(value = "usuario")
        CreateUsuarioDTO usuarioDTO
) {
}
