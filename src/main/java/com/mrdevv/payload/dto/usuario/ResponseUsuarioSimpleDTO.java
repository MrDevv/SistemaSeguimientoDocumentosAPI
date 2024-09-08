package com.mrdevv.payload.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"usuario_area_id", "nombres", "apellidos"})
public record ResponseUsuarioSimpleDTO(
        @JsonProperty(value = "usuario_area_id")
        Long UsuarioAreaId,
        String nombres,
        String apellidos
) {
}
