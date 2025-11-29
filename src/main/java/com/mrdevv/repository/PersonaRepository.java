package com.mrdevv.repository;

import com.mrdevv.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Boolean existsByDni(String dni);
}
