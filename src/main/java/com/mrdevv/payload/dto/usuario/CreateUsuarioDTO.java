package com.mrdevv.payload.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mrdevv.payload.dto.persona.CreatePersonaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUsuarioDTO(
        @NotNull
        @JsonProperty(value = "rol_id")
        Long rolId,
        @NotNull
        @Valid
        @JsonProperty(value = "persona")
        CreatePersonaDTO personaDTO,
        @NotBlank
        String password
) {
}
