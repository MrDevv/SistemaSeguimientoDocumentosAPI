package com.mrdevv.payload.dto.area;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAreaDTO(
    @Size(max = 200, message = "La longitud de la descripción debe ser menor a 200 carácteres")
    @NotBlank(message = "La descripción es requerida")
    String descripcion
){
}
