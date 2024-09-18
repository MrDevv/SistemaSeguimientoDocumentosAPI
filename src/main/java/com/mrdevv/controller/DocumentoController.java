package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.documento.CreateDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDTO;
import com.mrdevv.payload.dto.documento.ResponseDocumentoDetalladoDTO;
import com.mrdevv.payload.dto.documento.UpdateDocumentoDTO;
import com.mrdevv.service.IDocumentoService;
import com.mrdevv.utils.TipoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    private final IDocumentoService documentoService;

    @Autowired
    public DocumentoController(IDocumentoService documentoService){
        this.documentoService = documentoService;
    }

    @PostMapping
    public ResponseEntity<Object> crearDocumento(@Valid @RequestBody CreateDocumentoDTO documentoDTO){
        ResponseDocumentoDTO documento = documentoService.saveDocumento(documentoDTO);
        return ResponseHandler.get(TipoResponse.CREATE, "Documento registrado correctamente", documento);
    }

    @GetMapping
    public ResponseEntity<Object> listarDocumentos(@RequestParam(required = false) String estado,
                                                   @RequestParam(required = false, name = "numeroDoc") String numDocumento){
         List<ResponseDocumentoDetalladoDTO> documentos = documentoService.getAllDocumentos(estado, numDocumento);
        return ResponseHandler.get(TipoResponse.GETALL, "Listado de documentos", documentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarDocumento(@PathVariable Long id, @Valid @RequestBody UpdateDocumentoDTO documentoDTO){
        ResponseDocumentoDTO documento = documentoService.updateDocumento(id, documentoDTO);
        return ResponseHandler.get(TipoResponse.GET, "Documento actualizado correctamente", documento);
    }

    @PatchMapping("/{id}/finalizarSeguimiento")
    public ResponseEntity<Object> finalizarSeguimientoDocumento(@PathVariable(name = "id") Long idDocumento){
        documentoService.finalizarSeguimiento(idDocumento);
        return ResponseEntity.noContent().build();
    }

}
