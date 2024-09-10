package com.mrdevv.payload.mapper;

import com.mrdevv.model.Documento;
import com.mrdevv.model.DocumentoEstado;
import com.mrdevv.model.TipoDocumento;
import com.mrdevv.model.UsuarioArea;
import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;

public class DocumentoMapper {

    public static Documento toDocumentoEntity(CreateDocumentoDTO documentoDTO, Long id){
        return Documento.builder()
                .tipoDocumento(TipoDocumento.builder().id(documentoDTO.tipoDocumentoId()).build())
                .usuarioArea(UsuarioArea.builder().id(documentoDTO.usuarioAreaId()).build())
                .numDocumento(documentoDTO.numDocumento())
                .asunto(documentoDTO.asunto())
                .folios(documentoDTO.folios())
                .estado(DocumentoEstado.builder().id(id).build())
                .build();
    }

    public static ResponseDocumentoDTO toDocumentoDTO(Documento documento){
        return new ResponseDocumentoDTO(
                documento.getId(),
                documento.getTipoDocumento().getId(),
                documento.getUsuarioArea().getId(),
                documento.getNumDocumento(),
                documento.getAsunto(),
                documento.getFolios(),
                documento.getFechaRegistro(),
                documento.getEstado().getId()
        );
    }
}
