package com.mrdevv.payload.dto.envio;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record CreateEnvioDTO(
        @NotNull(message = "El id del documento es requerido")
        @JsonProperty("documento_id")
        Long documentoId,
        @NotNull(message = "El id del usuario origen es requerido")
        @JsonProperty("usuario_origen_id")
        Long usuarioAreaOrigenId,
        @NotNull(message = "El id del usuario destino es requerido")
        @JsonProperty("usuario_destino_id")
        Long usuarioAreaDestinoId,
        @NotNull(message = "El id de la indicación es requerida")
        @JsonProperty("indicacion_id")
        Long indicacionId,
        @NotNull(message = "Los folios son requeridos")
        Integer folios,
        String observacion
) {
}
