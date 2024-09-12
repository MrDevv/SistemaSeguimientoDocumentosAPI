package com.mrdevv.repository;

import com.mrdevv.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Procedure(procedureName = "sp_listar_documentos")
    List<Object[]> getDocumentos(@Param("estado_documento") String estado, @Param("numero_documento") String numDocumento);

}
