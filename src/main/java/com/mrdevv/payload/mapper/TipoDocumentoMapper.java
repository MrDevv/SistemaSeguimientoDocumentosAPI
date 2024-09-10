package com.mrdevv.payload.mapper;

import com.mrdevv.model.TipoDocumento;
import com.mrdevv.payload.dto.tipoDocumento.CreateTipoDocumentoDTO;
import com.mrdevv.payload.dto.tipoDocumento.ResponseTipoDocumentoDTO;

import java.util.List;

public class TipoDocumentoMapper {

    public static List<ResponseTipoDocumentoDTO> toTipoDocumentoListDTO(List<TipoDocumento> tipoDocumentos) {
        return tipoDocumentos.stream()
                .map(tipoDocumento -> new ResponseTipoDocumentoDTO(
                        tipoDocumento.getId(),
                        tipoDocumento.getDescripcion()
                )).toList();
    }

    public static TipoDocumento toTipoDocumentoEntity(CreateTipoDocumentoDTO tipoDocumentoDTO) {
        return TipoDocumento.builder()
                .descripcion(tipoDocumentoDTO.descripcion())
                .build();
    }

    public static ResponseTipoDocumentoDTO toTipoDocumentoDTO(TipoDocumento tipoDocumento) {
        return new ResponseTipoDocumentoDTO(
                tipoDocumento.getId(),
                tipoDocumento.getDescripcion()
        );
    }
}
