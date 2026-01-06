package com.mrdevv.service;

import com.mrdevv.model.Envio;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;

public interface IEnvioService {

    ResponseEnvioDTO saveEnvio(CreateEnvioDTO envioDTO);

    Envio findEnvioById(Long idEnvio);

    void cancelarEnvio(Long idEnvio);

}
