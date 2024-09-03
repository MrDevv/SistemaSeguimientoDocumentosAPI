package com.mrdevv.service.impl;

import com.mrdevv.model.Area;
import com.mrdevv.payload.dto.areas.CreateAreaDTO;
import com.mrdevv.payload.dto.areas.ResponseAreaDTO;
import com.mrdevv.payload.mapper.AreaMapper;
import com.mrdevv.repository.AreaRepository;
import com.mrdevv.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {

    private final AreaRepository areaRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository){
        this.areaRepository = areaRepository;
    }

    @Transactional
    @Override
    public List<ResponseAreaDTO> getAreas(Boolean estado, String area) {
        List<Area> areas = areaRepository.getAreas(estado, area);
        return AreaMapper.toAreaListDTO(areas);
    }

    @Override
    public ResponseAreaDTO getAreaById(Long id) {
        Area area = areaRepository.getReferenceById(id);
        return AreaMapper.toAreaDTO(area);
    }

    @Override
    public ResponseAreaDTO saveArea(CreateAreaDTO areaDTO) {
        Area area = areaRepository.save(AreaMapper.toAreaEntity(areaDTO));
        return AreaMapper.toAreaDTO(area);
    }

    @Override
    public ResponseAreaDTO updateArea(Long id, CreateAreaDTO areaDTO) {
        Area oldArea = areaRepository.findById(id)
                        .orElseThrow();

        oldArea.setDescripcion(areaDTO.descripcion());
        Area updatedArea = areaRepository.save(oldArea);

        return AreaMapper.toAreaDTO(updatedArea);
    }

    @Override
    public void disableArea(Long id) {
        if (areaRepository.existsById(id)){
            areaRepository.disableArea(id);
        }else{
            throw new RuntimeException();
        }
    }

    @Override
    public void enableArea(Long id) {
        if (areaRepository.existsById(id)){
            areaRepository.enableArea(id);
        }else{
            throw new RuntimeException();
        }
    }
}
