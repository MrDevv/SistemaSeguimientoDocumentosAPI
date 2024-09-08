package com.mrdevv.repository;

import com.mrdevv.model.Area;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioSimpleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Procedure(procedureName = "sp_listar_areas")
    List<Area> getAreas(@Param("estado_area") Boolean estado, @Param("nombre_area") String area);

    @Procedure(procedureName = "sp_listar_usuarios_activos_por_area")
    List<Object[]> getUsuariosByIdArea(@Param("id_area") Long id);

    @Modifying
    @Procedure(procedureName = "sp_deshabilitar_area")
    void disableArea(@Param("id_area") Long id);

    @Modifying
    @Procedure(procedureName = "sp_habilitar_area")
    void enableArea(@Param("id_area") Long id);

    boolean existsAreaByDescripcion(String descripcion);
}
