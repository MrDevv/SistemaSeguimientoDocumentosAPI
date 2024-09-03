package com.mrdevv.service;

import com.mrdevv.model.Area;
import com.mrdevv.payload.dto.areas.CreateAreaDTO;
import com.mrdevv.payload.dto.areas.ResponseAreaDTO;

import java.util.List;

public interface IAreaService {

    List<ResponseAreaDTO> getAreas(Boolean estado, String area);

    ResponseAreaDTO getAreaById(Long id);

    ResponseAreaDTO saveArea(CreateAreaDTO areaDTO);

    ResponseAreaDTO updateArea(Long id, CreateAreaDTO areaDTO);

    void disableArea(Long id);
    void enableArea(Long id);
}
