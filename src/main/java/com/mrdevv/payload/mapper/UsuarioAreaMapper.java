package com.mrdevv.payload.mapper;

import com.mrdevv.model.Area;
import com.mrdevv.model.Usuario;
import com.mrdevv.model.UsuarioArea;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;
import com.mrdevv.payload.dto.rol.ResponseRolDTO;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;
import com.mrdevv.payload.dto.usuario_area.CreateUsuarioAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioAreaMapper {

    public static List<ResponseUsuarioAreaDTO> toUsuarioAreaListDTO(List<Object[]> usuariosArea) {
        List<ResponseUsuarioAreaDTO> usuariosAreaDTO = new ArrayList<>();

        for (Object[] result : usuariosArea) {
            long idArea = ((Number) result[0]).longValue();
            Date fecha = (Date) result[1];
            String nombre = (String) result[2];

            long idUsuario = ((Number) result[3]).longValue();
            String nombreUsuario = (String) result[4];

            long idPersona = ((Number) result[5]).longValue();
            String nombrePersona = (String) result[6];
            String apellidoPersona = (String) result[7];
            String emailPersona = (String) result[8];
            String telefonoPersona = result[9] != null ? (String) result[9] : null;

            long idRol = ((Number) result[10]).longValue();
            String nombreRol = (String) result[11];

            String estado = UsuarioArea.convertirEstado((Character) result[12]);

            ResponsePersonaDTO personaDTO = new ResponsePersonaDTO(idPersona, nombrePersona, apellidoPersona, emailPersona, telefonoPersona);
            ResponseRolDTO rolDTO = new ResponseRolDTO(idRol, nombreRol);
            ResponseUsuarioDTO usuarioDTO = new ResponseUsuarioDTO(idUsuario, nombreUsuario, personaDTO, rolDTO);

            ResponseUsuarioAreaDTO usuarioAreaDTO = new ResponseUsuarioAreaDTO(idArea, fecha, nombre, usuarioDTO, estado);

            usuariosAreaDTO.add(usuarioAreaDTO);
        }

        return usuariosAreaDTO;
    }

    public static UsuarioArea toUsuarioAreaEntity(CreateUsuarioAreaDTO usuarioAreaDTO, ResponseUsuarioDTO usuarioDTO) {
        return UsuarioArea.builder()
                .usuario(UsuarioMapper.responseToUsuarioEntity(usuarioDTO))
                .area(Area.builder().id(usuarioAreaDTO.areaId()).build())
                .build();
    }

    public static UsuarioArea responseToUsuarioAreaEntity(ResponseUsuarioAreaDTO usuarioAreaDTO, ResponseUsuarioDTO usuarioDTO) {
        return UsuarioArea.builder()
                .usuario(UsuarioMapper.responseToUsuarioEntity(usuarioDTO))
                .area(Area.builder().descripcion(usuarioAreaDTO.area()).build())
//                .area(Area.builder().id(usuarioAreaDTO.areaId()).build())
                .build();
    }

    public static ResponseUsuarioAreaDTO toUsuarioAreaDTO(UsuarioArea usuarioArea){
        return new ResponseUsuarioAreaDTO(
                usuarioArea.getId(),
                usuarioArea.getFechaIngreso(),
                usuarioArea.getArea().getDescripcion(),
                UsuarioMapper.toUsuarioDTO(usuarioArea.getUsuario()),
                UsuarioArea.convertirEstado(usuarioArea.getEstado())
        );
    }
}
