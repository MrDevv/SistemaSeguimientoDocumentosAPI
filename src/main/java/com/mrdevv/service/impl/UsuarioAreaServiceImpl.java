package com.mrdevv.service.impl;

import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import com.mrdevv.payload.mapper.UsuarioAreaMapper;
import com.mrdevv.repository.UsuarioAreaRepository;
import com.mrdevv.service.IUsuarioAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioAreaServiceImpl implements IUsuarioAreaService {

    private final UsuarioAreaRepository usuarioAreaRepository;

    @Autowired
    public UsuarioAreaServiceImpl(UsuarioAreaRepository usuarioAreaRepository){
        this.usuarioAreaRepository = usuarioAreaRepository;
    }

    @Transactional
    @Override
    public List<ResponseUsuarioAreaDTO> getUsuariosArea() {
        List<Object[]> usuarioArea = usuarioAreaRepository.getUsuariosArea();
        return UsuarioAreaMapper.toUsuarioAreaListDTO(usuarioArea);
    }
}
