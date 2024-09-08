package com.mrdevv.payload.mapper;

import com.mrdevv.model.Persona;
import com.mrdevv.payload.dto.persona.CreatePersonaDTO;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;

public class PersonaMapper {

    public static Persona toPersonaEntity(CreatePersonaDTO personaDTO){
        return Persona.builder()
                .nombres(personaDTO.nombres())
                .apellidos(personaDTO.apellidos())
                .dni(personaDTO.dni())
                .celular(personaDTO.celular())
                .build();
    }

    public static Persona responseToPersonaEntity(ResponsePersonaDTO personaDTO){
        return Persona.builder()
                .id(personaDTO.id())
                .nombres(personaDTO.nombres())
                .apellidos(personaDTO.apellidos())
                .dni(personaDTO.dni())
                .celular(personaDTO.celular())
                .build();
    }

    public static ResponsePersonaDTO toPersonaDTO(Persona persona){
        return new ResponsePersonaDTO(
                persona.getId(),
                persona.getNombres(),
                persona.getApellidos(),
                persona.getDni(),
                persona.getCelular()
        );
    }
}