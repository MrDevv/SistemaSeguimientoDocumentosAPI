package com.mrdevv.service.impl;

import com.mrdevv.payload.dto.areas.ResponseAreaDTO;

import java.util.List;

public interface IAreaService {

    List<ResponseAreaDTO> getAreas(Boolean estado, String area);

    ResponseAreaDTO getAreaById(Long id);


}
