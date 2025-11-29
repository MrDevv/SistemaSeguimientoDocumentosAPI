package com.mrdevv.repository;

import com.mrdevv.model.UsuarioArea;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioAreaRepository extends JpaRepository<UsuarioArea, Long> {

    @Query(value = "select * from sp_listar_usuarios_area()", nativeQuery = true)
    Page<Object[]> getUsuariosArea(Pageable pageable);
}
