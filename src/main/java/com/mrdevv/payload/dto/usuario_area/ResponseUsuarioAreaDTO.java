package com.mrdevv.payload.dto.usuario_area;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;

import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder({"usuario_area_id", "fecha_ingreso", "area", "usuario", "isActive"})
public record ResponseUsuarioAreaDTO(
        @JsonProperty(value = "usuario_area_id")
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonProperty(value = "fecha_ingreso")
        Date fechaIngreso,
        String area,
        @JsonProperty(value = "usuario")
        ResponseUsuarioDTO usuarioDTO,
        String estado
) implements Serializable {
}
