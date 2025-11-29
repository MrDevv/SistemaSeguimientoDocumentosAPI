package com.mrdevv.service;

import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.persona.CreatePersonaDTO;
import com.mrdevv.payload.dto.persona.UpdatePersonaDTO;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;
import com.mrdevv.payload.dto.usuario_area.CreateUsuarioAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import org.springframework.data.domain.Pageable;

public interface IUsuarioAreaService {

    ResponseWithPageable<ResponseUsuarioAreaDTO> getUsuariosArea(Pageable pageable);

    ResponseUsuarioAreaDTO saveUsuarioArea(CreateUsuarioAreaDTO usuarioAreaDTO);

    ResponseUsuarioDTO updateUsuario(Long id, CreatePersonaDTO createPersonaDTO);
}
