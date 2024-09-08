package com.mrdevv.service;

import com.mrdevv.model.Persona;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;
import com.mrdevv.payload.dto.usuario.CreateUsuarioDTO;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;

public interface IUsuarioServices {

    ResponseUsuarioDTO saveUsuario(CreateUsuarioDTO usuarioDTO, ResponsePersonaDTO persona);

    void existsByUsername(String username);

}
