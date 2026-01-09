package com.mrdevv.repository;

import com.mrdevv.model.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query(value = "select * from sp_listar_documentos(:estado_documento, :numero_documento, :usuario_area_id, :area_id, :fecha_inicio, :fecha_fin)", nativeQuery = true)
    Page<Object[]> getDocumentos(@Param("estado_documento") String estado, @Param("numero_documento") String numDocumento,
                                 @Param("usuario_area_id") Long usuarioAreaId, @Param("area_id") Long areaId,
                                 @Param("fecha_inicio") String fechaInicio, @Param("fecha_fin") String fechaFin, Pageable pageable);

    boolean existsDocumentoByNumDocumento(String numDocumento);

    @Modifying
    @Query(value = "UPDATE MAE_DOCUMENTO SET DOCUMENTO_ESTADO_ID = :id_estado WHERE DOCUMENTO_ID = :id_documento", nativeQuery = true)
    void cambiarEstado(@Param("id_estado") Integer id, @Param("id_documento") Long idDocumento);
}
