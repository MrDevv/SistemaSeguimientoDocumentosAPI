package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectDuplicateExcepction;
import com.mrdevv.model.Indicacion;
import com.mrdevv.payload.dto.indicacion.CreateIndicacionDTO;
import com.mrdevv.payload.dto.indicacion.ResponseIndicacionDTO;
import com.mrdevv.payload.mapper.IndicacionMapper;
import com.mrdevv.repository.IndicacionRepository;
import com.mrdevv.service.IIndicacionService;
import com.mrdevv.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IndicacionServiceImpl implements IIndicacionService {

    private final IndicacionRepository indicacionRepository;

    @Autowired
    public IndicacionServiceImpl(IndicacionRepository indicacionRepository){
        this.indicacionRepository = indicacionRepository;
    }

    @Transactional
    @Override
    public List<ResponseIndicacionDTO> getIndicaciones(String descripcion) {
        List<Indicacion> indicaciones = indicacionRepository.getIndicaciones(descripcion);
        return IndicacionMapper.toIndicacionListDTO(indicaciones);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseIndicacionDTO getIndicacionById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public ResponseIndicacionDTO saveIndicacion(CreateIndicacionDTO indicacionDTO) {
        existsByDescripcion(indicacionDTO.descripcion());
        Indicacion indicacion = indicacionRepository.save(IndicacionMapper.toIndicacionEntity(indicacionDTO));
        return IndicacionMapper.toIndicacionDTO(indicacion);
    }

    @Transactional
    @Override
    public ResponseIndicacionDTO updateIndicacion(Long id, CreateIndicacionDTO indicacionDTO) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public void existsByDescripcion(String descripcion) {
        if (indicacionRepository.existsIndicacionByDescripcion(descripcion)){
            throw new ObjectDuplicateExcepction(
                    ErrorMessages.INDICACION_DUPLICATE_BACKEND.getMessage(descripcion),
                    ErrorMessages.INDICACION_DUPLICATE_FRONT.getMessage(descripcion)
            );
        }
    }


}
