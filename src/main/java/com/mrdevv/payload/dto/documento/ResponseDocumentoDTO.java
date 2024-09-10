package com.mrdevv.payload.dto.documento;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record ResponseDocumentoDTO (
        Long id,
        @JsonProperty("tipo_documento_id")
        Long idTipoDocumento,
        @JsonProperty("usuario_area_id")
        Long idUsuarioArea,
        @JsonProperty("numero_documento")
        String numDocumento,
        String asunto,
        Integer folios,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        @JsonProperty("fecha_registro")
        Date fechaRegistro,
        @JsonProperty("estado_documento_id")
        Long idEstado
){
}
