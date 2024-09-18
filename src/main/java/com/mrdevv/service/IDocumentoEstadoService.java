package com.mrdevv.service;

public interface IDocumentoEstadoService {

    Long getIdEstadoNuevo();

    Long getIdEstadoRecepcionado();

    Long getIdEstadoEnviado();

    Long getIdEstadoPendienteRecepcion();

    Integer getIdEstadoSeguimientoFinalizado();
}
