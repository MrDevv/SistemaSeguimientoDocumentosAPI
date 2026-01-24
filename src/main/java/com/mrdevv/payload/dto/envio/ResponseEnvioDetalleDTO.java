package com.mrdevv.payload.dto.envio;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"envio_id", "numero_documento", "tipo_documento", "folios", "indicacion", "usuario_origen",
        "area_origen", "usuario_destino", "area_destino", "observacion", "fecha_envio", "fecha_recepcion", "estado_envio", "estado_recepcion", "estado_documento"})
public record ResponseEnvioDetalleDTO(
        @JsonProperty("envio_id")
        Long envioId,
        @JsonProperty("numero_documento")
        String numeroDocumento,
        @JsonProperty("tipo_documento")
        String tipoDocumento,
        Integer folios,
        String indicacion,
        @JsonProperty("usuario_origen")
        String usuarioOrigen,
        @JsonProperty("area_origen")
        String areaOrigen,
        @JsonProperty("usuario_destino")
        String usuarioDestino,
        @JsonProperty("area_destino")
        String areaDestino,
        String observacion,
        @JsonProperty("fecha_envio")
        String fechaEnvio,
        @JsonProperty("fecha_recepcion")
        String fechaRecepcion,
        @JsonProperty("estado_envio")
        String estadoEnvio,
        @JsonProperty("estado_recepcion")
        String estadoRecepcion,
        @JsonProperty("estado_documento")
        String estadoDocumento
) {
}
