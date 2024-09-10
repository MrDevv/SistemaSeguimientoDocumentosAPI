package com.mrdevv.payload.dto.tipoDocumento;

import jakarta.validation.constraints.NotBlank;

public record CreateTipoDocumentoDTO(
        @NotBlank(message = "La descripción del tipo de documento es requerida")
        String descripcion
) {
}
