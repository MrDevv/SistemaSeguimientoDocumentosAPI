package com.mrdevv.service;

import com.mrdevv.model.Recepcion;
import com.mrdevv.payload.dto.recepcion.CreateRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionDTO;

public interface IRecepcionService {

    ResponseRecepcionDTO saveRecepcion(CreateRecepcionDTO recepcionDTO);

    Recepcion findRecepcionById(Long recepcionId);

    void confirmarRecepcion(Long recepcionId);

    void confirmarEnvio(Long recepcionId);

    void cancelarRecepcion(Long recepcionId);

    void validarEstadoRecepcionadoByIdEnvio(Long recepcionId);

    void validarEstadoPendienteDeRecepcionByIdEnvio(Long recepcionId);

    void eliminarRecepcionByEnvioId(Long idEnvio);
}
