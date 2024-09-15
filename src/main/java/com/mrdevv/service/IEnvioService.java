package com.mrdevv.service;

import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;

public interface IEnvioService {

    ResponseEnvioDTO saveEnvio(CreateEnvioDTO envioDTO);
}
