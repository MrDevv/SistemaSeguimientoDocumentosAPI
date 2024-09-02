package com.mrdevv.payload.mapper;

import com.mrdevv.model.Area;
import com.mrdevv.payload.dto.areas.ResponseAreaDTO;

import java.util.List;

public class AreaMapper {

    public static List<ResponseAreaDTO> toAreaListDTO(List<Area> areas){
        return areas.stream()
                .map(area -> new ResponseAreaDTO(area.getId(), area.getDescripcion(), area.getEstado()))
                .toList();
    }

    public static ResponseAreaDTO toAreaDTO(Area area){
        return new ResponseAreaDTO(area.getId(), area.getDescripcion(), area.getEstado());
    }
}
