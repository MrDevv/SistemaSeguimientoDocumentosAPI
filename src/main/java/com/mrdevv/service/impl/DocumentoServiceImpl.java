package com.mrdevv.service.impl;

import com.mrdevv.exception.ConflictExcepcion;
import com.mrdevv.exception.ObjectDuplicateExcepction;
import com.mrdevv.exception.ObjectNotFoundException;
import com.mrdevv.model.Documento;
import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDetalladoDTO;
import com.mrdevv.payload.dto.documento.UpdateDocumentoDTO;
import com.mrdevv.payload.mapper.DocumentoMapper;
import com.mrdevv.repository.DocumentoRepository;
import com.mrdevv.service.IDocumentoEstadoService;
import com.mrdevv.service.IDocumentoService;
import com.mrdevv.utils.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements IDocumentoService {

    private final DocumentoRepository documentoRepository;
    private final IDocumentoEstadoService documentoEstadoService;

    @Transactional(readOnly = true)
    @Override
    public ResponseWithPageable getAllDocumentos(String estado, String numDocumento, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Object[]> documentos = documentoRepository.getDocumentos(estado, numDocumento, pageable);
        return DocumentoMapper.toDocumentoListDTO(documentos);
    }

    @Transactional
    @Override
    public ResponseDocumentoDTO saveDocumento(CreateDocumentoDTO documentoDTO) {
        this.existsDocumentoByNumDocumento(documentoDTO.numDocumento());
        Long idEstadoNuevo = documentoEstadoService.getIdEstadoNuevo();
        Documento documento = documentoRepository.save(DocumentoMapper.toDocumentoEntity(documentoDTO, idEstadoNuevo));
        return DocumentoMapper.toDocumentoDTO(documento);
    }

    @Transactional
    @Override
    public ResponseDocumentoDTO updateDocumento(Long id, UpdateDocumentoDTO documentoDTO) {
        Documento oldDocumento = this.findDocumentoById(id);
        this.existsDocumentoByNumDocumento(documentoDTO.numDocumento());
//        TODO: validar si el estado del documento está en 'nuevo', si no está en nuevo no debería permitir actualizar
        DocumentoMapper.toDocumentoEntityUpdate(documentoDTO, oldDocumento);
        return DocumentoMapper.toDocumentoDTO(documentoRepository.save(oldDocumento));
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseDocumentoDTO getDocumentoById(Long id) {
        Documento documento = this.findDocumentoById(id);
        return DocumentoMapper.toDocumentoDTO(documento);
    }

    @Transactional(readOnly = true)
    @Override
    public void existsDocumentoByNumDocumento(String numDocumento) {
        if(documentoRepository.existsDocumentoByNumDocumento(numDocumento)){
            throw new ObjectDuplicateExcepction(
                    ErrorMessages.DOCUMENTO_DUPLICATE_BACKEND.getMessage(numDocumento),
                    ErrorMessages.DOCUMENTO_DUPLICATE_FRONT.getMessage(numDocumento)
            );
        }
    }

    @Override
    public void validarEstadoDocumentoEnSeguimiento(Long id) {
        Documento documento = findDocumentoById(id);
        if(documento.getEstado().getId().longValue() !=  documentoEstadoService.getIdEstadoEnSeguimiento()){
            throw new ConflictExcepcion(
                ErrorMessages.DOCUMENTO_FOLLOW_UP_COMPLETED_BACKEND.getMessage(id),
                ErrorMessages.DOCUMENTO_FOLLOW_UP_COMPLETED_FRONT.getMessage()
            );
        }
    }

    @Transactional
    @Override
    public void iniciarSeguimiento(Long idDocumento) {
        Integer idEstadoActivo = documentoEstadoService.getIdEstadoEnSeguimiento();
        documentoRepository.iniciarSeguimiento(idEstadoActivo, idDocumento);
    }

    @Transactional
    @Override
    public void finalizarSeguimiento(Long idDocumento) {
        Integer idEstadoFinalizado = documentoEstadoService.getIdEstadoSeguimientoFinalizado();
        documentoRepository.finalizarSeguimiento(idEstadoFinalizado, idDocumento);
    }

    private Documento findDocumentoById(Long id) {
        return documentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        ErrorMessages.DOCUMENTO_NOT_FOUND_BACKEND.getMessage(id),
                        ErrorMessages.DOCUMENTO_NOT_FOUND_FRONT.getMessage()
                ));
    }
}
