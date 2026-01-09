package com.mrdevv.service;

import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.dto.documento.UpdateDocumentoDTO;

import java.time.LocalDate;

public interface IDocumentoService {
    ResponseWithPageable getAllDocumentos(String estado, String numDocumento, Long usuarioAreaId, Long areaId, String fechaInicio, String fechaFin, Integer page, Integer size);

    ResponseDocumentoDTO saveDocumento(CreateDocumentoDTO documentoDTO);

    ResponseDocumentoDTO updateDocumento(Long id, UpdateDocumentoDTO documentoDTO);

    ResponseDocumentoDTO getDocumentoById(Long id);

    void existsDocumentoByNumDocumento(String numDocumento);

    void validarEstadoDocumentoEnSeguimientoONuevo(Long id);

    void iniciarSeguimiento(Long idDocumento);

    void finalizarSeguimiento(Long id);

    void cambiarEstadoSeguimientoANuevo(Long idDocumento);

}
