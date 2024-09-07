package com.mrdevv.payload.mapper;

import com.mrdevv.model.Rol;
import com.mrdevv.payload.dto.rol.ResponseRolDTO;

public class RolMapper {

    public static ResponseRolDTO toRolDTO(Rol rol){
        return new ResponseRolDTO(
                rol.getId(),
                rol.getDescripcion()
        );
    }
}
