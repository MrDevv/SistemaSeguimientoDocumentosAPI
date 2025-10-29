package com.mrdevv.payload.mapper;

import com.mrdevv.model.Area;
import com.mrdevv.payload.dto.PageableData;
import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.area.CreateAreaDTO;
import com.mrdevv.payload.dto.area.ResponseAreaDTO;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioSimpleDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaSimpleDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class AreaMapper {

    public static ResponseWithPageable<ResponseAreaDTO> toAreaListDTO(Page<Area> areas){
        PageableData pageable = PageableMapper.toPageableData(areas);
        List<ResponseAreaDTO> areaDTOS = areas.stream()
                .map(area -> new ResponseAreaDTO(area.getId(), area.getDescripcion(), area.getEstado()))
                .toList();

        return new ResponseWithPageable(areaDTOS, pageable);
    }

    public static ResponseAreaDTO toAreaDTO(Area area){
        return new ResponseAreaDTO(area.getId(), area.getDescripcion(), area.getEstado());
    }

    public static Area toAreaEntity(CreateAreaDTO areaDTO){
        return Area.builder()
                    .descripcion(areaDTO.descripcion())
                    .build();
    }

    public static Area responseToAreaEntity(ResponseAreaDTO areaDTO){
        return Area.builder()
                .id(areaDTO.id())
                .descripcion(areaDTO.descripcion())
                .build();
    }

    public static List<ResponseUsuarioSimpleDTO> toUsuarioSimpleDTO(List<Object[]> usuariosArea){
        List<ResponseUsuarioSimpleDTO> usuariosSimpleDTO = new ArrayList<>();

        for (Object[] result : usuariosArea){
            long usuarioAreaId = ((Number) result[0]).longValue();
            String nombres = (String) result[1];
            String apellidos = (String) result[2];

            ResponseUsuarioSimpleDTO usuarioSimple = new ResponseUsuarioSimpleDTO(usuarioAreaId, nombres, apellidos);
            usuariosSimpleDTO.add(usuarioSimple);
        }

        return usuariosSimpleDTO;
    }
}
