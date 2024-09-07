package com.mrdevv.repository;

import com.mrdevv.model.UsuarioArea;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface UsuarioAreaRepository extends JpaRepository<UsuarioArea, Long> {

    @Procedure(procedureName = "sp_listar_usuarios_area")
    List<Object[]> getUsuariosArea();
}
