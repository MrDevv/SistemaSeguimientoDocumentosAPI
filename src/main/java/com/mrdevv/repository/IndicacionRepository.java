package com.mrdevv.repository;

import com.mrdevv.model.Indicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndicacionRepository extends JpaRepository<Indicacion, Long> {
    @Procedure(procedureName = "sp_listar_indicaciones")
    List<Indicacion> getIndicaciones(@Param("indicacion_descripcion") String descripcion);

    boolean existsIndicacionByDescripcion(String descripcion);
}
