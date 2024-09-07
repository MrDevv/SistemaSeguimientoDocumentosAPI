package com.mrdevv.payload.mapper;

import com.mrdevv.model.Usuario;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;

public class UsuarioMapper {

    public static ResponseUsuarioDTO toUsuarioDTO(Usuario usuario){
        return new ResponseUsuarioDTO(
                usuario.getId(),
                usuario.getNombreUsuario(),
                PersonaMapper.toPersonaDTO(usuario.getPersona()),
                RolMapper.toRolDTO(usuario.getRol())
        );
    }
}
