package com.mrdevv.service;

import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDetalladoDTO;
import com.mrdevv.payload.dto.documento.UpdateDocumentoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDocumentoService {
    ResponseWithPageable getAllDocumentos(String estado, String numDocumento, Integer page, Integer size);

    ResponseDocumentoDTO saveDocumento(CreateDocumentoDTO documentoDTO);

    ResponseDocumentoDTO updateDocumento(Long id, UpdateDocumentoDTO documentoDTO);

    ResponseDocumentoDTO getDocumentoById(Long id);

    void existsDocumentoByNumDocumento(String numDocumento);

    void iniciarSeguimiento(Long idDocumento);

    void finalizarSeguimiento(Long id);

}
