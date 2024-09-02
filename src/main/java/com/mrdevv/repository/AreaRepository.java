package com.mrdevv.repository;

import com.mrdevv.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Procedure(procedureName = "sp_listar_areas")
    List<Area> getAreas(@Param("estado_area") Boolean estado, @Param("nombre_area") String area);
}
