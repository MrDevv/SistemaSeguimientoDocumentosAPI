package com.mrdevv.service;

import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.area.CreateAreaDTO;
import com.mrdevv.payload.dto.area.ResponseAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaSimpleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAreaService {

    ResponseWithPageable<ResponseAreaDTO> getAreas(Boolean estado, String area, Pageable pageable
    );

    ResponseUsuarioAreaSimpleDTO getUsuariosActivosPorArea(Long idArea);

    ResponseAreaDTO getAreaById(Long id);

    ResponseAreaDTO saveArea(CreateAreaDTO areaDTO);

    ResponseAreaDTO updateArea(Long id, CreateAreaDTO areaDTO);

    void disableArea(Long id);
    void enableArea(Long id);

    void existsAreaById(Long id);

    void existsByDescripcion(String descripcion);
}
