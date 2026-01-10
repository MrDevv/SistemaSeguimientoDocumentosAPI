package com.mrdevv.service.impl;

import com.mrdevv.exception.ConflictExcepcion;
import com.mrdevv.exception.ObjectNotFoundException;
import com.mrdevv.model.Recepcion;
import com.mrdevv.payload.dto.recepcion.CreateRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionEstadoSimpleDTO;
import com.mrdevv.payload.mapper.RecepcionMapper;
import com.mrdevv.repository.RecepcionRepository;
import com.mrdevv.service.IDocumentoEstadoService;
import com.mrdevv.service.IDocumentoService;
import com.mrdevv.service.IRecepcionService;
import com.mrdevv.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecepcionServiceImpl implements IRecepcionService {

    private final RecepcionRepository recepcionRepository;
    private final IDocumentoEstadoService documentoEstadoService;
    private final IDocumentoService documentoService;

    @Transactional
    @Override
    public ResponseRecepcionDTO saveRecepcion(CreateRecepcionDTO recepcionDTO) {
        Long estadoPendienteRecepcionId = documentoEstadoService.getIdEstadoPendienteRecepcion();
        Recepcion recepcion = recepcionRepository.save(RecepcionMapper.toRecepcionEntity(recepcionDTO, estadoPendienteRecepcionId));
        return RecepcionMapper.toRecepcionDTO(recepcion);
    }


    @Override
    public Recepcion findRecepcionById(Long recepcionId) {
        return recepcionRepository.findById(recepcionId.longValue()).orElseThrow(() -> {
            throw new ObjectNotFoundException(
                    ErrorMessages.RECEPCION_NOT_FOUND_BACKEND.getMessage(recepcionId),
                    ErrorMessages.RECEPCION_NOT_FOUND_FRONT.getMessage()
            );
        });
    }


    @Transactional
    @Override
    public void confirmarRecepcion(Long recepcionId) {
        Recepcion recepcion = findRecepcionById(recepcionId);
        documentoService.validarEstadoDocumentoEnSeguimientoONuevo(recepcion.getEnvio().getDocumento().getId());
        Long idEstadoRecepcionado = documentoEstadoService.getIdEstadoRecepcionado();
        recepcionRepository.confirmarRecepcion(recepcionId, idEstadoRecepcionado);
    }

    @Override
    public void confirmarEnvio(Long recepcionId) {
        Long idEstadoEnviado = documentoEstadoService.getIdEstadoEnviado();
        recepcionRepository.confirmarEnvioRecepcion(recepcionId, idEstadoEnviado);
    }

    @Transactional
    @Override
    public void cancelarRecepcion(Long recepcionId) {
        Recepcion recepcion = findRecepcionById(recepcionId);
        documentoService.validarEstadoDocumentoEnSeguimientoONuevo(recepcion.getEnvio().getDocumento().getId());
        Integer estadoPendienteRecepcionId = documentoEstadoService.getIdEstadoPendienteRecepcion().intValue();
        recepcionRepository.cancelarRecepcion(recepcionId, estadoPendienteRecepcionId);
    }

    @Override
    public void validarEstadoRecepcionadoByIdEnvio(Long envioId) {
        Recepcion recepcion = recepcionRepository.getEstadoRecepcionByIdEnvio(envioId);
        Long documentoId = recepcion.getEnvio().getDocumento().getId();
        if (recepcion.getEstadoRecepcion().getId() != documentoEstadoService.getIdEstadoRecepcionado()){
            throw new ConflictExcepcion(
                    ErrorMessages.DOCUMENTO_PENDING_RECEPCION_BACKEND.getMessage(documentoId),
                    ErrorMessages.DOCUMENTO_PENDING_RECEPCION_FRONT.getMessage()
            );
        }
    }

    @Override
    public void validarEstadoPendienteDeRecepcionByIdEnvio(Long envioId) {
        Recepcion recepcion = recepcionRepository.getEstadoRecepcionByIdEnvio(envioId);
        Long documentoId = recepcion.getEnvio().getDocumento().getId();
        if (recepcion.getEstadoRecepcion().getId() != documentoEstadoService.getIdEstadoPendienteRecepcion()){
            throw new ConflictExcepcion(
                    ErrorMessages.DOCUMENTO_RECEPCION_COMPLETED_BACKEND.getMessage(documentoId),
                    ErrorMessages.DOCUMENTO_RECEPCION_COMPLETED_FRONT.getMessage()
            );
        }
    }

    @Transactional
    @Override
    public void eliminarRecepcionByEnvioId(Long idEnvio) {
        recepcionRepository.eliminarRecepcionByEnvioId(idEnvio);
    }


}
