package com.mrdevv.payload.dto.rol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"rol_id", "descripcion"})
public record ResponseRolDTO(
        @JsonProperty(value = "rol_id")
        Long id,
        String descripcion
) implements Serializable {
}
