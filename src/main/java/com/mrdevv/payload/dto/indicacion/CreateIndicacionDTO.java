package com.mrdevv.payload.dto.indicacion;

import jakarta.validation.constraints.NotBlank;

public record CreateIndicacionDTO(
        @NotBlank(message = "La descripción de la indicación es requerida")
        String descripcion
) {
}
