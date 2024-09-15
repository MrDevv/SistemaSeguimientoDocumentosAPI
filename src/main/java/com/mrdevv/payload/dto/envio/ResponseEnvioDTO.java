package com.mrdevv.payload.dto.envio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record ResponseEnvioDTO(
        Long envioId,
        @JsonProperty("documento_id")
        Long documentoId,
        @JsonProperty("usuario_origen_id")
        Long usuarioAreaOrigenId,
        @JsonProperty("usuario_destino_id")
        Long usuarioAreaDestinoId,
        @JsonProperty("indicacion_id")
        Long indicacionId,
        Integer folios,
        String observacion,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonProperty("fecha_envio")
        Date fechaEnvio
) {
}
