package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectNotFoundException;
import com.mrdevv.model.Rol;
import com.mrdevv.payload.dto.rol.ResponseRolDTO;
import com.mrdevv.payload.mapper.RolMapper;
import com.mrdevv.repository.RolRepository;
import com.mrdevv.service.IRolService;
import com.mrdevv.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements IRolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public ResponseRolDTO rolById(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        ErrorMessages.ROL_NOT_FOUND_BACKEND.getMessage(id),
                        ErrorMessages.ROL_NOT_FOUND_FRONT.getMessage()
                ));

        return RolMapper.toRolDTO(rol);
    }
}
