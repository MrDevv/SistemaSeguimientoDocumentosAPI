package com.mrdevv.payload.dto.areas;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseAreaDTO(
        Long id,
        String descripcion,
        @JsonProperty(value = "isActive")
        Boolean estado
) {
}
