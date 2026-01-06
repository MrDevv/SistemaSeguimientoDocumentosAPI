package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectNotFoundException;
import com.mrdevv.exception.ConflictExcepcion;
import com.mrdevv.model.DocumentoEstado;
import com.mrdevv.model.Envio;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;
import com.mrdevv.payload.dto.recepcion.CreateRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionEstadoSimpleDTO;
import com.mrdevv.payload.mapper.EnvioMapper;
import com.mrdevv.repository.EnvioRepository;
import com.mrdevv.service.IDocumentoEstadoService;
import com.mrdevv.service.IDocumentoService;
import com.mrdevv.service.IEnvioService;
import com.mrdevv.service.IRecepcionService;
import com.mrdevv.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class EnvioServiceImpl implements IEnvioService {

    private final EnvioRepository envioRepository;
    private final IRecepcionService recepcionService;
    private final IDocumentoService documentoService;
    private final IDocumentoEstadoService estadoService;

    @Transactional
    @Override
    public ResponseEnvioDTO saveEnvio(CreateEnvioDTO envioDTO) {
        ResponseDocumentoDTO documentoDTO = documentoService.getDocumentoById(envioDTO.documentoId());
        if (Objects.equals(documentoDTO.idEstado(), estadoService.getIdEstadoNuevo())){
            documentoService.iniciarSeguimiento(envioDTO.documentoId());
        }
        ResponseRecepcionEstadoSimpleDTO estadoRecepcion = recepcionService.getEstadoRecepcionByDocumento(envioDTO.documentoId());
        this.validarEstadoRecepcionDeDocumento(estadoRecepcion, envioDTO.documentoId());
        Envio envioDB = EnvioMapper.toEnvioEntity(envioDTO);
        Long idEstadoEnviado = estadoService.getIdEstadoEnviado();
        envioDB.setDocumentoEstado(DocumentoEstado.builder().id(idEstadoEnviado).build());
        Envio envio = envioRepository.save(envioDB);
        recepcionService.saveRecepcion(new CreateRecepcionDTO(envio.getId(), envioDTO.usuarioAreaDestinoId()));
        return EnvioMapper.toEnvioDTO(envio);
    }

    @Override
    public Envio findEnvioById(Long idEnvio) {
        return envioRepository.findById(idEnvio).orElseThrow(() -> {
            throw new ObjectNotFoundException(
                    ErrorMessages.ENVIO_NOT_FOUND_BACKEND.getMessage(idEnvio),
                    ErrorMessages.ENVIO_NOT_FOUND_FRONT.getMessage()
            );
        });
    }

    @Transactional
    @Override
    public void cancelarEnvio(Long idEnvio) {
        Envio envio = this.findEnvioById(idEnvio);
        documentoService.validarEstadoDocumentoEnSeguimiento(envio.getDocumento().getId());
        recepcionService.validarEstadoPendienteDeRecepcionByIdEnvio(idEnvio);
        recepcionService.eliminarRecepcionByEnvioId(idEnvio);
        envioRepository.deleteById(idEnvio);
    }

    private void validarEstadoRecepcionDeDocumento(ResponseRecepcionEstadoSimpleDTO estadoRecepcionDocumento, Long idDocumento){
        if (estadoRecepcionDocumento.estadoRecepcion() != null && estadoRecepcionDocumento.estadoRecepcion().equalsIgnoreCase("pendiente recepcion")){
            throw new ConflictExcepcion(
                    ErrorMessages.DOCUMENTO_PENDING_RECEPCION_BACKEND.getMessage(idDocumento),
                    ErrorMessages.DOCUMENTO_PENDING_RECEPCION_FRONT.getMessage()
            );
        }
    }
}
