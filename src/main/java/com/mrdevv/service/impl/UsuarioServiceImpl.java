package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectDuplicateExcepction;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements IUsuarioServices {

    private final UsuarioRepository usuarioRepository;

    private final IPersonaService personaService;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, IPersonaService personaService){
        this.usuarioRepository = usuarioRepository;
        this.personaService = personaService;
    }

    @Transactional
    @Override
    public ResponseUsuarioDTO saveUsuario(CreateUsuarioDTO usuarioDTO, ResponsePersonaDTO personaDTO) {
        existsByUsername(usuarioDTO.nombreUsuario());
        Usuario usuario = usuarioRepository.save(UsuarioMapper.toUsuarioEntity(usuarioDTO, personaDTO));
        return UsuarioMapper.toUsuarioDTO(usuario);
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
}
