package com.mrdevv.service.impl;

import com.mrdevv.model.Usuario;
import com.mrdevv.model.UsuarioArea;
import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.area.ResponseAreaDTO;
import com.mrdevv.payload.dto.persona.CreatePersonaDTO;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;
import com.mrdevv.payload.dto.persona.UpdatePersonaDTO;
import com.mrdevv.payload.dto.rol.ResponseRolDTO;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;
import com.mrdevv.payload.dto.usuario_area.CreateUsuarioAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import com.mrdevv.payload.mapper.*;
import com.mrdevv.repository.UsuarioAreaRepository;
import com.mrdevv.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioAreaServiceImpl implements IUsuarioAreaService {

    private final UsuarioAreaRepository usuarioAreaRepository;

    private final IAreaService areaService;
    private final IPersonaService personaService;
    private final IUsuarioServices usuarioService;
    private final IRolService rolService;

    @Transactional(readOnly = true)
    @Override
    public ResponseWithPageable getUsuariosArea(Pageable pageable) {
        Page<Object[]> usuarioArea = usuarioAreaRepository.getUsuariosArea(pageable);
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

    @Transactional
    @Override
    public ResponseUsuarioDTO updateUsuario(Long id, CreatePersonaDTO createPersonaDTO) {
        Usuario usuario = usuarioService.findById(id);
        personaService.updatePersona(usuario.getPersona().getId(), createPersonaDTO);
        usuario.generarUserName();
        usuarioService.updateNombreUsuario(usuario.getId(), usuario.getNombreUsuario());
        return UsuarioMapper.toUsuarioDTO(usuario);
    }
}
