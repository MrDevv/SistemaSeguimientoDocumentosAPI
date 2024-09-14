package com.mrdevv.service.impl;

import com.mrdevv.exception.ObjectNotFoundException;
import com.mrdevv.model.Documento;
import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDetalladoDTO;
import com.mrdevv.payload.dto.documento.UpdateDocumentoDTO;
import com.mrdevv.payload.mapper.DocumentoMapper;
import com.mrdevv.repository.DocumentoRepository;
import com.mrdevv.service.IDocumentoEstadoService;
import com.mrdevv.service.IDocumentoService;
import com.mrdevv.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

    private final DocumentoRepository documentoRepository;
    private final IDocumentoEstadoService documentoEstadoService;

    @Autowired
    public DocumentoServiceImpl(DocumentoRepository documentoRepository, IDocumentoEstadoService documentoEstadoService) {
        this.documentoRepository = documentoRepository;
        this.documentoEstadoService = documentoEstadoService;
    }

    @Transactional
    @Override
    public List<ResponseDocumentoDetalladoDTO> getAllDocumentos(String estado, String numDocumento) {
        List<Object[]> documentos = documentoRepository.getDocumentos(estado, numDocumento);
        return DocumentoMapper.toDocumentoListDTO(documentos);
    }

    @Transactional
    @Override
    public ResponseDocumentoDTO saveDocumento(CreateDocumentoDTO documentoDTO) {
        Long idEstadoNuevo = documentoEstadoService.getIdEstadoNuevo();
        Documento documento = documentoRepository.save(DocumentoMapper.toDocumentoEntity(documentoDTO, idEstadoNuevo));
        return DocumentoMapper.toDocumentoDTO(documento);
    }

    @Override
    public ResponseDocumentoDTO updateDocumento(Long id, UpdateDocumentoDTO documentoDTO) {
        Documento oldDocumento = this.findDocumentoById(id);
//        TODO: validar si el estado del documento está en 'nuevo', si no está en nuevo no debería permitir actualizar
        DocumentoMapper.toDocumentoEntityUpdate(documentoDTO, oldDocumento);
        return DocumentoMapper.toDocumentoDTO(documentoRepository.save(oldDocumento));
    }

    private Documento findDocumentoById(Long id) {
        return documentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        ErrorMessages.DOCUMENTO_NOT_FOUND_BACKEND.getMessage(id),
                        ErrorMessages.DOCUMENTO_NOT_FOUND_FRONT.getMessage()
                ));
    }
}
