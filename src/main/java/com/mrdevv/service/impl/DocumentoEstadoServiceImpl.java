package com.mrdevv.service.impl;

import com.mrdevv.repository.DocumentoEstadoRepository;
import com.mrdevv.service.IDocumentoEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoEstadoServiceImpl implements IDocumentoEstadoService {

    private final DocumentoEstadoRepository documentoEstadoRepository;

    @Autowired
    public DocumentoEstadoServiceImpl(DocumentoEstadoRepository documentoEstadoRepository){
        this.documentoEstadoRepository = documentoEstadoRepository;
    }

    @Override
    public Long getIdEstadoNuevo() {
        return documentoEstadoRepository.getIdEstadoNuevoDocumento();
    }
}
