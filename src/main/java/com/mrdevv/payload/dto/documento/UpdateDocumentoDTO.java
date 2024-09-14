package com.mrdevv.payload.dto.documento;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateDocumentoDTO(
        @NotNull(message = "El id del tipo de documento es requerido")
        @JsonProperty(value = "tipo_documento_id")
        Long tipoDocumentoId,
        @NotBlank(message = "El numero del documento es requerido")
        @JsonProperty(value = "numero_documento")
        String numDocumento,
        @NotBlank(message = "El asunto del documento es requerido")
        String asunto,
        @NotNull(message = "Los folios son requeridos")
        Integer folios
) {
}
