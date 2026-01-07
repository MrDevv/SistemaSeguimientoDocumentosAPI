package com.mrdevv.repository;

import com.mrdevv.model.Recepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecepcionRepository extends JpaRepository<Recepcion, Long> {

    @Query(value = "select * from trs_recepcion where envio_id = :envio_id", nativeQuery = true)
    Recepcion getEstadoRecepcionByIdEnvio(@Param("envio_id") Long envioId);

    @Modifying
    @Query(value = "UPDATE TRS_RECEPCION SET DOCUMENTO_ESTADO_ID = :estado_id WHERE RECEPCION_ID = :recepcion_id", nativeQuery = true)
    int confirmarRecepcion(@Param("recepcion_id") Long recepcionId, @Param("estado_id") Long estadoRecepcionadoId);

    @Modifying
    @Query(value = "UPDATE TRS_RECEPCION SET DOCUMENTO_ESTADO_ID = :estado_id WHERE RECEPCION_ID = :recepcion_id", nativeQuery = true)
    int confirmarEnvioRecepcion(@Param("recepcion_id") Long recepcionId, @Param("estado_id") Long estadoEnviadoId);

    @Modifying
    @Query(value = "DELETE FROM trs_recepcion WHERE envio_id = :envio_id", nativeQuery = true)
    void eliminarRecepcionByEnvioId(@Param("envio_id") Long envioId);

}
