package com.mrdevv.service.impl;

import com.mrdevv.model.Recepcion;
import com.mrdevv.payload.dto.recepcion.CreateRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionEstadoSimpleDTO;
import com.mrdevv.payload.mapper.RecepcionMapper;
import com.mrdevv.repository.RecepcionRepository;
import com.mrdevv.service.IDocumentoEstadoService;
import com.mrdevv.service.IRecepcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecepcionServiceImpl implements IRecepcionService {

    private final RecepcionRepository recepcionRepository;
    private final IDocumentoEstadoService documentoEstadoService;

    @Autowired
    public RecepcionServiceImpl(RecepcionRepository recepcionRepository, IDocumentoEstadoService documentoEstadoService){
        this.recepcionRepository = recepcionRepository;
        this.documentoEstadoService = documentoEstadoService;
    }

    @Transactional
    @Override
    public ResponseRecepcionDTO saveRecepcion(CreateRecepcionDTO recepcionDTO) {
        Long estadoPendienteRecepcionId = documentoEstadoService.getIdEstadoPendienteRecepcion();
        Recepcion recepcion = recepcionRepository.save(RecepcionMapper.toRecepcionEntity(recepcionDTO, estadoPendienteRecepcionId));
        return RecepcionMapper.toRecepcionDTO(recepcion);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseRecepcionEstadoSimpleDTO getEstadoRecepcionByDocumento(Long documentoId) {
        List<Object[]> estadoRecepcion = recepcionRepository.getEstadoRecepcionByDocumento(documentoId);
        return RecepcionMapper.toRecepcionEstadoSimpleDTO(estadoRecepcion);
    }

    @Transactional
    @Override
    public void confirmarRecepcion(Long recepcionId) {
        Long idEstadoRecepcionado = documentoEstadoService.getIdEstadoRecepcionado();
        System.out.println(idEstadoRecepcionado);
        Integer status = recepcionRepository.confirmarRecepcion(recepcionId, idEstadoRecepcionado);
        System.out.println(status);
    }

    @Override
    public void confirmarEnvio(Long recepcionId) {
        Long idEstadoEnviado = documentoEstadoService.getIdEstadoEnviado();
        recepcionRepository.confirmarEnvioRecepcion(recepcionId, idEstadoEnviado);
    }


}
