package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectNotFoundException;
import com.mrdevv.model.TipoDocumento;
import com.mrdevv.payload.dto.tipoDocumento.CreateTipoDocumentoDTO;
import com.mrdevv.payload.dto.tipoDocumento.ResponseTipoDocumentoDTO;
import com.mrdevv.payload.mapper.TipoDocumentoMapper;
import com.mrdevv.repository.TipoDocumentoRepository;
import com.mrdevv.service.ITipoDocumentoService;
import com.mrdevv.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    public TipoDocumentoServiceImpl(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Transactional
    @Override
    public List<ResponseTipoDocumentoDTO> getTiposDocumento(String descripcion) {
        List<TipoDocumento> tiposDocumento = tipoDocumentoRepository.getTiposDocumento(descripcion);
        return TipoDocumentoMapper.toTipoDocumentoListDTO(tiposDocumento);
    }

    @Override
    public ResponseTipoDocumentoDTO getTipoDocumentoById(Long id) {
        TipoDocumento tipoDocumento = findTipoDocumentoById(id);
        return TipoDocumentoMapper.toTipoDocumentoDTO(tipoDocumento);
    }

    @Override
    public ResponseTipoDocumentoDTO saveTipoDocumento(CreateTipoDocumentoDTO tipoDocumentoDTO) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository.save(TipoDocumentoMapper.toTipoDocumentoEntity(tipoDocumentoDTO));
        return TipoDocumentoMapper.toTipoDocumentoDTO(tipoDocumento);
    }

    @Override
    public ResponseTipoDocumentoDTO updateTipoDocumento(Long id, CreateTipoDocumentoDTO tipoDocumentoDTO) {
        TipoDocumento tipoDocumento = findTipoDocumentoById(id);
        tipoDocumento.setDescripcion(tipoDocumentoDTO.descripcion());
        return TipoDocumentoMapper.toTipoDocumentoDTO(tipoDocumentoRepository.save(tipoDocumento));
    }

    private TipoDocumento findTipoDocumentoById(Long id){
        return tipoDocumentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        ErrorMessages.TIPO_DOCUMENTO_NOT_FOUND_BACKEND.getMessage(id),
                        ErrorMessages.TIPO_DOCUMENTO_NOT_FOUND_FRONT.getMessage()
                ));
    }
}
