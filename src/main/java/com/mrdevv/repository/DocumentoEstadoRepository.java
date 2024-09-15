package com.mrdevv.repository;

import com.mrdevv.model.DocumentoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DocumentoEstadoRepository extends JpaRepository<DocumentoEstado, Long> {
    @Query("SELECT d.id FROM DocumentoEstado d WHERE d.descripcion = 'nuevo'")
    Long getIdEstadoNuevoDocumento();

    @Query("SELECT d.id FROM DocumentoEstado d WHERE d.descripcion = 'recepcionado'")
    Long getIdEstadoDocumentoRecepcionado();

    @Query("SELECT d.id FROM DocumentoEstado d WHERE d.descripcion = 'enviado'")
    Long getIdEstadoDocumentoEnviado();

    @Query("SELECT d.id FROM DocumentoEstado d WHERE d.descripcion = 'pendiente recepcion'")
    Long getIdEstadoDocumentoPendienteRecepcion();
}
