package com.mrdevv.service;

import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDetalladoDTO;

import java.util.List;

public interface IDocumentoService {
    List<ResponseDocumentoDetalladoDTO> getAllDocumentos(String estado, String numDocumento);

    ResponseDocumentoDTO saveDocumento(CreateDocumentoDTO documentoDTO);

}
