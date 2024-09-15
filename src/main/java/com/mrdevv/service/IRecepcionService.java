package com.mrdevv.service;

import com.mrdevv.payload.dto.recepcion.CreateRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionEstadoSimpleDTO;

public interface IRecepcionService {

    ResponseRecepcionDTO saveRecepcion(CreateRecepcionDTO recepcionDTO);

    ResponseRecepcionEstadoSimpleDTO getEstadoRecepcionByDocumento(Long documentoId);

    void confirmarRecepcion(Long recepcionId);

    void confirmarEnvio(Long recepcionId);
}
