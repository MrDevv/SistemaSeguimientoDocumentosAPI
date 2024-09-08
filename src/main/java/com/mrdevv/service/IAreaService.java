package com.mrdevv.service;

import com.mrdevv.payload.dto.area.CreateAreaDTO;
import com.mrdevv.payload.dto.area.ResponseAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaSimpleDTO;

import java.util.List;

public interface IAreaService {

    List<ResponseAreaDTO> getAreas(Boolean estado, String area);

    ResponseUsuarioAreaSimpleDTO getUsuariosActivosPorArea(Long idArea);

    ResponseAreaDTO getAreaById(Long id);

    ResponseAreaDTO saveArea(CreateAreaDTO areaDTO);

    ResponseAreaDTO updateArea(Long id, CreateAreaDTO areaDTO);

    void disableArea(Long id);
    void enableArea(Long id);

    void existsAreaById(Long id);

    void existsByDescripcion(String descripcion);
}
