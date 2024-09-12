package com.mrdevv.payload.dto.documento;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrdevv.payload.dto.tipoDocumento.ResponseTipoDocumentoDTO;

import java.util.Date;

@JsonPropertyOrder({"id_documento", "numero_documento","tipo_documento", "usuario_registrador", "area", "asunto", "folios", "fecha_registro", "estado"})
public record ResponseDocumentoDetalladoDTO(
        @JsonProperty(value = "id_documento")
        Long id,
        @JsonProperty(value = "numero_documento")
        String numeroDocumento,
        @JsonProperty(value = "tipo_documento")
        ResponseTipoDocumentoDTO tipoDocumento,
        @JsonProperty(value = "usuario_registrador")
        String usuarioRegistrador,
        String area,
        String asunto,
        Integer folios,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonProperty(value = "fecha_registro")
        Date fechaRegistro,
        String estado
){
}
