package com.mrdevv.service;

import com.mrdevv.model.UsuarioArea;
import com.mrdevv.payload.dto.usuario_area.CreateUsuarioAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface IUsuarioAreaService {

    List<ResponseUsuarioAreaDTO> getUsuariosArea();

    ResponseUsuarioAreaDTO saveUsuarioArea(CreateUsuarioAreaDTO usuarioAreaDTO);
}
