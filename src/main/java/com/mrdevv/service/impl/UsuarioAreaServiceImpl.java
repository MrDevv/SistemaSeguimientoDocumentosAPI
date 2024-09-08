package com.mrdevv.service.impl;

import com.mrdevv.model.UsuarioArea;
import com.mrdevv.payload.dto.area.ResponseAreaDTO;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;
import com.mrdevv.payload.dto.rol.ResponseRolDTO;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;
import com.mrdevv.payload.dto.usuario_area.CreateUsuarioAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import com.mrdevv.payload.mapper.AreaMapper;
import com.mrdevv.payload.mapper.RolMapper;
import com.mrdevv.payload.mapper.UsuarioAreaMapper;
import com.mrdevv.repository.UsuarioAreaRepository;
import com.mrdevv.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioAreaServiceImpl implements IUsuarioAreaService {

    private final UsuarioAreaRepository usuarioAreaRepository;

    private final IAreaService areaService;
    private final IPersonaService personaService;
    private final IUsuarioServices usuarioService;
    private final IRolService rolService;

    @Autowired
    public UsuarioAreaServiceImpl(UsuarioAreaRepository usuarioAreaRepository, IAreaService areaService,
                                  IPersonaService personaService, IUsuarioServices usuarioService, IRolService rolService){
        this.usuarioAreaRepository = usuarioAreaRepository;
        this.areaService = areaService;
        this.personaService = personaService;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @Transactional
    @Override
    public List<ResponseUsuarioAreaDTO> getUsuariosArea() {
        List<Object[]> usuarioArea = usuarioAreaRepository.getUsuariosArea();
        return UsuarioAreaMapper.toUsuarioAreaListDTO(usuarioArea);
    }

    @Transactional
    @Override
    public ResponseUsuarioAreaDTO saveUsuarioArea(CreateUsuarioAreaDTO usuarioAreaDTO) {
        ResponseAreaDTO areaDTO = areaService.getAreaById(usuarioAreaDTO.areaId());
        ResponseRolDTO rolDTO = rolService.rolById(usuarioAreaDTO.usuarioDTO().rolId());
        ResponsePersonaDTO personaDTO = personaService.savePersona(usuarioAreaDTO.usuarioDTO().personaDTO());
        ResponseUsuarioDTO usuarioDTO = usuarioService.saveUsuario(usuarioAreaDTO.usuarioDTO(), personaDTO);
        UsuarioArea usuarioArea = usuarioAreaRepository.save(UsuarioAreaMapper.toUsuarioAreaEntity(usuarioAreaDTO, usuarioDTO));
        usuarioArea.setArea(AreaMapper.responseToAreaEntity(areaDTO));
        usuarioArea.getUsuario().setRol(RolMapper.toRolEntity(rolDTO));

        return UsuarioAreaMapper.toUsuarioAreaDTO(usuarioArea);
    }
}
