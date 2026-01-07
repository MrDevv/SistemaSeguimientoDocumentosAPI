package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectNotFoundException;
import com.mrdevv.model.DocumentoEstado;
import com.mrdevv.model.Envio;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;
import com.mrdevv.payload.dto.recepcion.CreateRecepcionDTO;
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
        documentoService.validarEstadoDocumentoEnSeguimientoONuevo(documentoDTO.id());
        if (Objects.equals(documentoDTO.idEstado(), estadoService.getIdEstadoNuevo())){
            documentoService.iniciarSeguimiento(envioDTO.documentoId());
        }

        Envio envio = envioRepository.getUltimoEnvioByIdDocumento(envioDTO.documentoId());
        if (envio != null){
            recepcionService.validarEstadoRecepcionadoByIdEnvio(envio.getId());
        }

        envio = EnvioMapper.toEnvioEntity(envioDTO);
        Long idEstadoEnviado = estadoService.getIdEstadoEnviado();
        envio.setDocumentoEstado(DocumentoEstado.builder().id(idEstadoEnviado).build());
        envio = envioRepository.save(envio);
        recepcionService.saveRecepcion(new CreateRecepcionDTO(envio.getId(), envioDTO.usuarioAreaDestinoId()));
        return EnvioMapper.toEnvioDTO(envio);
    }

    @Transactional(readOnly = true)
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
        documentoService.validarEstadoDocumentoEnSeguimientoONuevo(envio.getDocumento().getId());
        recepcionService.validarEstadoPendienteDeRecepcionByIdEnvio(idEnvio);
        recepcionService.eliminarRecepcionByEnvioId(idEnvio);
        envioRepository.deleteById(idEnvio);
    }
}
