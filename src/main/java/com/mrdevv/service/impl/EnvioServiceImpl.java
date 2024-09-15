package com.mrdevv.service.impl;

import com.mrdevv.exception.PendingReceptionExcepcion;
import com.mrdevv.model.Envio;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;
import com.mrdevv.payload.dto.recepcion.CreateRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionEstadoSimpleDTO;
import com.mrdevv.payload.mapper.EnvioMapper;
import com.mrdevv.repository.EnvioRepository;
import com.mrdevv.service.IDocumentoService;
import com.mrdevv.service.IEnvioService;
import com.mrdevv.service.IRecepcionService;
import com.mrdevv.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnvioServiceImpl implements IEnvioService {

    private final EnvioRepository envioRepository;
    private final IRecepcionService recepcionService;
    private final IDocumentoService documentoService;

    @Autowired
    public EnvioServiceImpl(EnvioRepository envioRepository, IRecepcionService recepcionService, IDocumentoService documentoService) {
        this.envioRepository = envioRepository;
        this.recepcionService = recepcionService;
        this.documentoService = documentoService;
    }

    @Transactional
    @Override
    public ResponseEnvioDTO saveEnvio(CreateEnvioDTO envioDTO) {
        ResponseRecepcionEstadoSimpleDTO estadoRecepcion = recepcionService.getEstadoRecepcionByDocumento(envioDTO.documentoId());
        this.validarEstadoRecepcionDeDocumento(estadoRecepcion, envioDTO.documentoId());
        this.cambiarEstadoRecepcionEnviado(estadoRecepcion);

        Envio envio = envioRepository.save(EnvioMapper.toEnvioEntity(envioDTO));
        recepcionService.saveRecepcion(new CreateRecepcionDTO(envio.getId(), envioDTO.usuarioAreaDestinoId()));
        return EnvioMapper.toEnvioDTO(envio);
    }

    private void validarEstadoRecepcionDeDocumento(ResponseRecepcionEstadoSimpleDTO estadoRecepcionDocumento, Long idDocumento){
        if (estadoRecepcionDocumento.estadoRecepcion() != null && estadoRecepcionDocumento.estadoRecepcion().equalsIgnoreCase("pendiente recepcion")){
            throw new PendingReceptionExcepcion(
                    ErrorMessages.DOCUMENTO_PENDING_RECEPCION_BACKEND.getMessage(idDocumento),
                    ErrorMessages.DOCUMENTO_PENDING_RECEPCION_FRONT.getMessage()
            );
        }
    }

    private void cambiarEstadoRecepcionEnviado(ResponseRecepcionEstadoSimpleDTO estadoRecepcionDocumento){
        if(estadoRecepcionDocumento.recepcionId() != null && estadoRecepcionDocumento.estadoRecepcion().equalsIgnoreCase("recepcionado")){
            recepcionService.confirmarEnvio(estadoRecepcionDocumento.recepcionId());
        }
    }
}
