package com.mrdevv.payload.mapper;

import com.mrdevv.model.Documento;
import com.mrdevv.model.DocumentoEstado;
import com.mrdevv.model.TipoDocumento;
import com.mrdevv.model.UsuarioArea;
import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDetalladoDTO;
import com.mrdevv.payload.dto.documento.UpdateDocumentoDTO;
import com.mrdevv.payload.dto.tipoDocumento.ResponseTipoDocumentoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentoMapper {

    public static Documento toDocumentoEntity(CreateDocumentoDTO documentoDTO, Long id) {
        return Documento.builder()
                .tipoDocumento(TipoDocumento.builder().id(documentoDTO.tipoDocumentoId()).build())
                .usuarioArea(UsuarioArea.builder().id(documentoDTO.usuarioAreaId()).build())
                .numDocumento(documentoDTO.numDocumento())
                .asunto(documentoDTO.asunto())
                .folios(documentoDTO.folios())
                .estado(DocumentoEstado.builder().id(id).build())
                .build();
    }

    public static void toDocumentoEntityUpdate(UpdateDocumentoDTO newDocumento, Documento oldDocumento){
        oldDocumento.setTipoDocumento(TipoDocumento.builder().id(newDocumento.tipoDocumentoId()).build());
        oldDocumento.setNumDocumento(newDocumento.numDocumento());
        oldDocumento.setAsunto(newDocumento.asunto());
        oldDocumento.setFolios(newDocumento.folios());
    }

    public static ResponseDocumentoDTO toDocumentoDTO(Documento documento) {
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

    public static List<ResponseDocumentoDetalladoDTO> toDocumentoListDTO(List<Object[]> documentosDb) {
        List<ResponseDocumentoDetalladoDTO> documentos = new ArrayList();

        documentosDb.stream().forEach(documento -> {
            Long idDocumento = ((Number) documento[0]).longValue();
            String numeroDocumento = (String) documento[1];
            Long idTipoDocumento = ((Number) documento[2]).longValue();
            String tipoDocumento = (String) documento[3];
            String usuarioRegistrador = (String) documento[4];
            String area = (String) documento[5];
            String asunto = (String) documento[6];
            Integer folios = ((Number) documento[7]).intValue();
            Date fechaRegistro = (Date) documento[8];
            String estadoDocumento = (String) documento[9];

            ResponseTipoDocumentoDTO tipoDocumentoDTO = new ResponseTipoDocumentoDTO(
                    idTipoDocumento,
                    tipoDocumento
            );

            ResponseDocumentoDetalladoDTO documentoDetalladoDTO = new ResponseDocumentoDetalladoDTO(
                    idDocumento,
                    numeroDocumento,
                    tipoDocumentoDTO,
                    usuarioRegistrador,
                    area,
                    asunto,
                    folios,
                    fechaRegistro,
                    estadoDocumento
            );

            documentos.add(documentoDetalladoDTO);
        });
        return documentos;
    }
}
