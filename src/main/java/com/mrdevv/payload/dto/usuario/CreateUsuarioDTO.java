package com.mrdevv.payload.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrdevv.payload.dto.persona.CreatePersonaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateUsuarioDTO(
        @NotNull(message = "El id del rol es requerido")
        @Min(value = 1, message = "El rol id debe ser un número positivo")
        @JsonProperty(value = "rol_id")
        Long rolId,
        @NotNull
        @Valid
        @JsonProperty(value = "persona")
        CreatePersonaDTO personaDTO,
        @NotBlank(message = "La contraseña es requerida")
        String password
) {
}
