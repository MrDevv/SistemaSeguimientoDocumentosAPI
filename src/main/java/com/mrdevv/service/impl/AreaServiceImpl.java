package com.mrdevv.service.impl;

import com.mrdevv.model.Area;
import com.mrdevv.payload.dto.areas.ResponseAreaDTO;
import com.mrdevv.payload.mapper.AreaMapper;
import com.mrdevv.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService{

    private AreaRepository areaRepository;

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
}
