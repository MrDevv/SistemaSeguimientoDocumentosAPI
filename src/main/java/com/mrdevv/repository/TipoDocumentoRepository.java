package com.mrdevv.repository;

import com.mrdevv.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

    @Procedure(procedureName = "sp_listar_tipos_documento")
    List<TipoDocumento> getTiposDocumento(@Param("tipo_doc_descripcion") String descripcion);
}
