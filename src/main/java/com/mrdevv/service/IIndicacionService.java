package com.mrdevv.service;

import com.mrdevv.payload.dto.indicacion.CreateIndicacionDTO;
import com.mrdevv.payload.dto.indicacion.ResponseIndicacionDTO;

import java.util.List;

public interface IIndicacionService {

    List<ResponseIndicacionDTO> getIndicaciones(String descripcion);

    ResponseIndicacionDTO getIndicacionById(Long id);

    ResponseIndicacionDTO saveIndicacion(CreateIndicacionDTO indicacionDTO);

    ResponseIndicacionDTO updateIndicacion(Long id, CreateIndicacionDTO indicacionDTO);

    void existsByDescripcion(String descripcion);
}
