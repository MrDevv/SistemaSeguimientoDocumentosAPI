package com.mrdevv.service;

import com.mrdevv.model.Persona;
import com.mrdevv.model.Usuario;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;
import com.mrdevv.payload.dto.usuario.CreateUsuarioDTO;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;

public interface IUsuarioServices {

    ResponseUsuarioDTO saveUsuario(CreateUsuarioDTO usuarioDTO, ResponsePersonaDTO persona);

    void updateNombreUsuario(Long usuarioId, String nombreUsuario);

    void existsByUsername(String username);

    Usuario findById(Long id);

}
