package com.mrdevv.service;

import com.mrdevv.payload.dto.tipoDocumento.CreateTipoDocumentoDTO;
import com.mrdevv.payload.dto.tipoDocumento.ResponseTipoDocumentoDTO;

import java.util.List;

public interface ITipoDocumentoService {

    List<ResponseTipoDocumentoDTO> getTiposDocumento(String descripcion);

    ResponseTipoDocumentoDTO getTipoDocumentoById(Long id);

    ResponseTipoDocumentoDTO saveTipoDocumento(CreateTipoDocumentoDTO tipoDocumentoDTO);

    ResponseTipoDocumentoDTO updateTipoDocumento(Long id, CreateTipoDocumentoDTO tipoDocumentoDTO);
}
