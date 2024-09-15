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

    @Query(value = ("SELECT r.recepcion_id, er.descripcion 'estado_recepcion' FROM MAE_DOCUMENTO d "+
            "INNER JOIN TRS_ENVIO e ON d.documento_id = e.documento_id "+
            "INNER JOIN TRS_RECEPCION r ON e.envio_id = r.envio_id " +
            "INNER JOIN MAE_DOCUMENTO_ESTADO er ON r.documento_estado_id = er.documento_estado_id " +
            "WHERE d.documento_id = :documento_id")
            , nativeQuery = true)
    List<Object[]> getEstadoRecepcionByDocumento(@Param("documento_id") Long documentoId);


    @Modifying
    @Query(value = "UPDATE TRS_RECEPCION SET DOCUMENTO_ESTADO_ID = :estado_id WHERE RECEPCION_ID = :recepcion_id", nativeQuery = true)
    int confirmarRecepcion(@Param("recepcion_id") Long recepcionId, @Param("estado_id") Long estadoRecepcionadoId);

    @Modifying
    @Query(value = "UPDATE TRS_RECEPCION SET DOCUMENTO_ESTADO_ID = :estado_id WHERE RECEPCION_ID = :recepcion_id", nativeQuery = true)
    int confirmarEnvioRecepcion(@Param("recepcion_id") Long recepcionId, @Param("estado_id") Long estadoEnviadoId);
}
