package com.mrdevv.payload.dto.recepcion;

import java.util.Date;

public record ResponseRecepcionDTO(
        Long recepcionId,
        Long envioId,
        Long usuarioAreaId,
        Date fechaRecepcion,
        Long documentoEstadoId
) {
}
