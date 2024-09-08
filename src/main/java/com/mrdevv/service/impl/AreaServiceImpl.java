package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectDuplicateExcepction;
import com.mrdevv.exception.ObjectNotFoundException;
import com.mrdevv.model.Area;
import com.mrdevv.payload.dto.area.CreateAreaDTO;
import com.mrdevv.payload.dto.area.ResponseAreaDTO;
import com.mrdevv.payload.mapper.AreaMapper;
import com.mrdevv.repository.AreaRepository;
import com.mrdevv.service.IAreaService;
import com.mrdevv.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {
    private final AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Transactional
    @Override
    public List<ResponseAreaDTO> getAreas(Boolean estado, String area) {
        List<Area> areas = areaRepository.getAreas(estado, area);
        return AreaMapper.toAreaListDTO(areas);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseAreaDTO getAreaById(Long id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        ErrorMessages.AREA_NOT_FOUND_BACKEND.getMessage(id),
                        ErrorMessages.AREA_NOT_FOUND_FRONT.getMessage()));

        return AreaMapper.toAreaDTO(area);
    }

    @Transactional
    @Override
    public ResponseAreaDTO saveArea(CreateAreaDTO areaDTO) {
        existsByDescripcion(areaDTO.descripcion());
        Area area = areaRepository.save(AreaMapper.toAreaEntity(areaDTO));
        return AreaMapper.toAreaDTO(area);
    }

    @Transactional
    @Override
    public ResponseAreaDTO updateArea(Long id, CreateAreaDTO areaDTO) {
        Area oldArea = areaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        ErrorMessages.AREA_NOT_FOUND_BACKEND.getMessage(id),
                        ErrorMessages.AREA_NOT_FOUND_FRONT.getMessage()));

        existsByDescripcion(areaDTO.descripcion());

        oldArea.setDescripcion(areaDTO.descripcion());
        Area updatedArea = areaRepository.save(oldArea);

        return AreaMapper.toAreaDTO(updatedArea);
    }

    @Transactional
    @Override
    public void disableArea(Long id) {
        this.existsAreaById(id);
        areaRepository.disableArea(id);
    }

    @Transactional
    @Override
    public void enableArea(Long id) {
        this.existsAreaById(id);
        areaRepository.enableArea(id);
    }

    @Override
    public void existsAreaById(Long id) {
        if (!areaRepository.existsById(id)) {
            throw new ObjectNotFoundException(
                    ErrorMessages.AREA_NOT_FOUND_BACKEND.getMessage(id),
                    ErrorMessages.AREA_NOT_FOUND_FRONT.getMessage()
            );
        }
    }

    @Override
    public void existsByDescripcion(String descripcion) {
        if (areaRepository.existsAreaByDescripcion(descripcion)) {
            throw new ObjectDuplicateExcepction(
                    ErrorMessages.AREA_DUPLICATE_BACKEND.getMessage(descripcion),
                    ErrorMessages.AREA_DUPLICATE_FRONT.getMessage(descripcion)
            );
        }
    }
}
