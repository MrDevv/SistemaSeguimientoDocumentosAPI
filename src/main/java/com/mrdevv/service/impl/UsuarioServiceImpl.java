package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectDuplicateExcepction;
import com.mrdevv.exception.ObjectNotFoundException;
import com.mrdevv.model.Persona;
import com.mrdevv.model.Usuario;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;
import com.mrdevv.payload.dto.usuario.CreateUsuarioDTO;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;
import com.mrdevv.payload.mapper.UsuarioMapper;
import com.mrdevv.repository.UsuarioRepository;
import com.mrdevv.service.IPersonaService;
import com.mrdevv.service.IUsuarioServices;
import com.mrdevv.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioServices {

    private final UsuarioRepository usuarioRepository;

    private final IPersonaService personaService;

    @Transactional
    @Override
    public ResponseUsuarioDTO saveUsuario(CreateUsuarioDTO usuarioDTO, ResponsePersonaDTO personaDTO) {
        Usuario usuario = UsuarioMapper.toUsuarioEntity(usuarioDTO, personaDTO);
        usuario.generarUserName();
        existsByUsername(usuario.getNombreUsuario());
        usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioDTO(usuario);
    }

    @Transactional
    @Override
    public void updateNombreUsuario(Long usuarioId, String nombreUsuario) {
        usuarioRepository.updateUserName(usuarioId, nombreUsuario);
    }

    @Override
    public void existsByUsername(String username) {
        if(usuarioRepository.existsByNombreUsuario(username)){
            throw new ObjectDuplicateExcepction(
                    ErrorMessages.USUARIO_DUPLICATE_BACKEND.getMessage(username),
                    ErrorMessages.USUARIO_DUPLICATE_FRONT.getMessage(username)
            );
        }
    }

    @Transactional
    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow( () ->
                new ObjectNotFoundException(
                        "El objecto con ID " + id + " no se encuentra en la base de datos",
                        "El usuario con ID " + id + " no se encuentra registrado"
                )
        );
    }
}
