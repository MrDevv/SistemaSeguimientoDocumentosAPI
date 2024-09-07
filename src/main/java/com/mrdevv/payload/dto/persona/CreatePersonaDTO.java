package com.mrdevv.payload.dto.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreatePersonaDTO(
        @NotBlank
        String nombres,
        @NotBlank
        String apellidos,
        @NotBlank
        @Size(min = 8, max = 8, message = "El dni debe tener exactamente 8 digitos")
        @Pattern(regexp = "\\d+", message = "El DNI debe contener solo números")
        String dni,
        @Size(min = 9, max = 9, message = "El celular debe tener exactamente 9 digitos")
        @Pattern(regexp = "\\d+", message = "El celular debe contener solo números")
        String celular
) {
}
