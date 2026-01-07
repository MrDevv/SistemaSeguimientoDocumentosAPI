package com.mrdevv.repository;

import com.mrdevv.model.Documento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query(value = "select * from sp_listar_documentos(:estado_documento, :numero_documento)", nativeQuery = true)
    Page<Object[]> getDocumentos(@Param("estado_documento") String estado, @Param("numero_documento") String numDocumento, Pageable pageable);

    boolean existsDocumentoByNumDocumento(String numDocumento);

    @Modifying
    @Query(value = "UPDATE MAE_DOCUMENTO SET DOCUMENTO_ESTADO_ID = :id_estado WHERE DOCUMENTO_ID = :id_documento", nativeQuery = true)
    void cambiarEstado(@Param("id_estado") Integer id, @Param("id_documento") Long idDocumento);
}
