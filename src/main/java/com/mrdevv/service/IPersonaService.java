package com.mrdevv.service;

import com.mrdevv.model.Persona;
import com.mrdevv.payload.dto.persona.CreatePersonaDTO;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;

public interface IPersonaService {

    ResponsePersonaDTO savePersona(CreatePersonaDTO personaDTO);

    ResponsePersonaDTO updatePersona(Long id, CreatePersonaDTO personaDTO);

    Boolean existsPersonaByDni(String dni);
}
