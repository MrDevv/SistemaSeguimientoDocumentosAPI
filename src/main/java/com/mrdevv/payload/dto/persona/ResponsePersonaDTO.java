package com.mrdevv.payload.dto.persona;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"persona_id", "nombres", "apellidos", "dni", "celular"})
public record ResponsePersonaDTO(
        @JsonProperty(value = "persona_id")
        Long id,
        String nombres,
        String apellidos,
        String dni,
        String celular
) implements Serializable {
}
