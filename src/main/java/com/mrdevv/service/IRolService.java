package com.mrdevv.service;

import com.mrdevv.model.Rol;
import com.mrdevv.payload.dto.rol.ResponseRolDTO;

public interface IRolService {

    ResponseRolDTO rolById(Long id);
}
