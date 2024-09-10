package com.mrdevv.payload.dto.documento;


import com.mrdevv.model.TipoDocumento;

import java.util.Date;

public record ResponseDocumentoDetalladoDTO(
        Long id,
        TipoDocumento tipoDocumento,
        String usuarioRegistrador,
        String area,
        String asunto,
        Integer folios,
        Date fechaRegistro,
        String estado
){
}
