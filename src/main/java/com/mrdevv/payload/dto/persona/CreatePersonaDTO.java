package com.mrdevv.payload.dto.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreatePersonaDTO(
        @NotBlank(message = "El nombre es requerido")
        @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$", message = "El nombre solo puede contener letras")
        String nombres,
        @NotBlank(message = "Los apellidos son requeridos")
        @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$", message = "Los apellidos solo pueden contener letras")
        String apellidos,
        @NotBlank(message = "El DNI es requerido")
        @Size(min = 8, max = 8, message = "El dni debe tener exactamente 8 digitos")
        @Pattern(regexp = "\\d+", message = "El DNI debe contener solo números")
        String dni,
        @Size(min = 9, max = 9, message = "El celular debe tener exactamente 9 digitos")
        @Pattern(regexp = "\\d+", message = "El celular debe contener solo números")
        String celular
) {
}
