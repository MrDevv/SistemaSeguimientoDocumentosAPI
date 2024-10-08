package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectDuplicateExcepction;
import com.mrdevv.model.Persona;
import com.mrdevv.payload.dto.persona.CreatePersonaDTO;
import com.mrdevv.payload.dto.persona.ResponsePersonaDTO;
import com.mrdevv.payload.mapper.PersonaMapper;
import com.mrdevv.repository.PersonaRepository;
import com.mrdevv.service.IPersonaService;
import com.mrdevv.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements IPersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

    @Transactional
    @Override
    public ResponsePersonaDTO savePersona(CreatePersonaDTO personaDTO) {
        if (existsPersonaByDni(personaDTO.dni())){
          throw new ObjectDuplicateExcepction(ErrorMessages.PERSONA_DUPLICATE_BACKEND.getMessage(personaDTO.dni()),
                  ErrorMessages.PERSONA_DUPLICATE_FRONT.getMessage(personaDTO.dni()));
        };
        Persona persona = personaRepository.save(PersonaMapper.toPersonaEntity(personaDTO));
        return PersonaMapper.toPersonaDTO(persona);
    }

    @Transactional
    @Override
    public ResponsePersonaDTO updatePersona(Long id, CreatePersonaDTO personaDTO) {
    //    TODO: actualizar persona
        return null;
    }

    @Override
    public Boolean existsPersonaByDni(String dni) {
        return personaRepository.existsByDni(dni);
    }
}
