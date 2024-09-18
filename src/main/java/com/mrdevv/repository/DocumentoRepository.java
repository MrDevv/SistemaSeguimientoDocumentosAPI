package com.mrdevv.repository;

import com.mrdevv.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Procedure(procedureName = "sp_listar_documentos")
    List<Object[]> getDocumentos(@Param("estado_documento") String estado, @Param("numero_documento") String numDocumento);

    boolean existsDocumentoByNumDocumento(String numDocumento);

    @Modifying
    @Query(value = "UPDATE MAE_DOCUMENTO SET DOCUMENTO_ESTADO_ID = :id_estado_finalizado WHERE DOCUMENTO_ID = :id_documento", nativeQuery = true)
    void finalizarSeguimiento(@Param("id_estado_finalizado") Integer id, @Param("id_documento") Long idDocumento);
}
