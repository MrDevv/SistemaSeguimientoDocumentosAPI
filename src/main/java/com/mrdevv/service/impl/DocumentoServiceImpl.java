package com.mrdevv.service.impl;

import com.mrdevv.model.Documento;
import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.mapper.DocumentoMapper;
import com.mrdevv.repository.DocumentoRepository;
import com.mrdevv.service.IDocumentoEstadoService;
import com.mrdevv.service.IDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

    private final DocumentoRepository documentoRepository;
    private final IDocumentoEstadoService documentoEstadoService;

    @Autowired
    public DocumentoServiceImpl(DocumentoRepository documentoRepository, IDocumentoEstadoService documentoEstadoService){
        this.documentoRepository = documentoRepository;
        this.documentoEstadoService = documentoEstadoService;
    }

    @Transactional
    @Override
    public ResponseDocumentoDTO saveDocumento(CreateDocumentoDTO documentoDTO) {
        Long idEstadoNuevo = documentoEstadoService.getIdEstadoNuevo();
        Documento documento = documentoRepository.save(DocumentoMapper.toDocumentoEntity(documentoDTO, idEstadoNuevo));
        return DocumentoMapper.toDocumentoDTO(documento);
    }
}
