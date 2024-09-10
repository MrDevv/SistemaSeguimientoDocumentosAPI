package com.mrdevv.service;

import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;

public interface IDocumentoService {

    ResponseDocumentoDTO saveDocumento(CreateDocumentoDTO documentoDTO);
}
