package com.mrdevv.repository;

import com.mrdevv.model.Envio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {


    @Query(value = "select * from sp_listar_envios(:numero_documento, :usuario_area_id, :area_id, :fecha_inicio, :fecha_fin)", nativeQuery = true)
    Page<Object[]> obtenerEnvios(@Param("numero_documento") String numeroDocumento, @Param("usuario_area_id") Long usuarioAreaId,
                                 @Param("area_id") Long areaId, @Param("fecha_inicio") String fechaInicio, @Param("fecha_fin") String fechaFin, Pageable pageable);

    @Query(value = "select * from trs_envio where documento_id = :documento_id order by fecha_envio desc limit 1", nativeQuery = true)
    Envio getUltimoEnvioByIdDocumento(@Param("documento_id") Long documentoId);

}
