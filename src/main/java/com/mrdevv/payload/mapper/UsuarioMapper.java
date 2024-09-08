package com.mrdevv.payload.mapper;

import com.mrdevv.model.Persona;
import com.mrdevv.model.Rol;
import com.mrdevv.model.Usuario;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;
import com.mrdevv.payload.dto.usuario.CreateUsuarioDTO;
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

    public static Usuario toUsuarioEntity(CreateUsuarioDTO usuarioDTO, ResponsePersonaDTO personaDTO){
        return Usuario.builder()
                .password(usuarioDTO.password())
                .persona(PersonaMapper.responseToPersonaEntity(personaDTO))
                .rol(Rol.builder().id(usuarioDTO.rolId()).build())
                .build();
    }

    public static Usuario responseToUsuarioEntity(ResponseUsuarioDTO usuarioDTO){
        return Usuario.builder()
                .id(usuarioDTO.id())
                .nombreUsuario(usuarioDTO.nombreUsuario())
                .persona(PersonaMapper.responseToPersonaEntity(usuarioDTO.personaDTO()))
                .rol(RolMapper.toRolEntity(usuarioDTO.rolDTO()))
                .build();
    }
}
