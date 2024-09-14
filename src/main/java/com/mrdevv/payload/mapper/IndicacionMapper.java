package com.mrdevv.payload.mapper;

import com.mrdevv.model.Indicacion;
import com.mrdevv.payload.dto.indicacion.CreateIndicacionDTO;
import com.mrdevv.payload.dto.indicacion.ResponseIndicacionDTO;

import java.util.List;

public class IndicacionMapper {

    public static Indicacion toIndicacionEntity(CreateIndicacionDTO indicacionDTO) {
        return Indicacion.builder()
                .descripcion(indicacionDTO.descripcion())
                .build();
    }

    public static ResponseIndicacionDTO toIndicacionDTO(Indicacion indicacion) {
        return new ResponseIndicacionDTO(
                indicacion.getId(),
                indicacion.getDescripcion()
        );
    }

    public static List<ResponseIndicacionDTO> toIndicacionListDTO(List<Indicacion> indicaciones) {
        return indicaciones.stream().map(indicacion -> new ResponseIndicacionDTO(
                        indicacion.getId(),
                        indicacion.getDescripcion()
                )
        ).toList();
    }
}
