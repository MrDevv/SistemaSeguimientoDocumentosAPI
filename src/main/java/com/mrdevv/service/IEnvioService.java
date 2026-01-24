package com.mrdevv.service;

import com.mrdevv.model.Envio;
import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;

public interface IEnvioService {

    ResponseWithPageable listarEnvios(String numDocumento, Long usuarioAreaId, Long areaId, String fechaInicio, String fechaFin, Integer page, Integer size);

    ResponseEnvioDTO saveEnvio(CreateEnvioDTO envioDTO);

    Envio findEnvioById(Long idEnvio);

    void cancelarEnvio(Long idEnvio);

}
