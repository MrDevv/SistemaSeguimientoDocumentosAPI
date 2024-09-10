package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.tipoDocumento.CreateTipoDocumentoDTO;
import com.mrdevv.payload.dto.tipoDocumento.ResponseTipoDocumentoDTO;
import com.mrdevv.service.ITipoDocumentoService;
import com.mrdevv.utils.TipoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoDocumento")
public class TipoDocumentoController {

    private final ITipoDocumentoService tipoDocumentoService;

    @Autowired
    public TipoDocumentoController(ITipoDocumentoService tipoDocumentoService){
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @GetMapping
    public ResponseEntity<Object> listarTiposDocumento(@RequestParam(required = false) String descripcion){
        List<ResponseTipoDocumentoDTO> tiposDocumento = tipoDocumentoService.getTiposDocumento(descripcion);
        return ResponseHandler.get(TipoResponse.GETALL, "Listado de los tipos de documento", tiposDocumento);
    }

    @PostMapping
    public ResponseEntity<Object> crearTipoDocumento(@Valid @RequestBody CreateTipoDocumentoDTO tipoDocumentoDTO){
        ResponseTipoDocumentoDTO tipoDocumento = tipoDocumentoService.saveTipoDocumento(tipoDocumentoDTO);
        return ResponseHandler.get(TipoResponse.CREATE, "Tipo documento registrado correctamente", tipoDocumento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerTipoDocumentoById(@PathVariable Long id){
        ResponseTipoDocumentoDTO tipoDocumento =  tipoDocumentoService.getTipoDocumentoById(id);
        return ResponseHandler.get(TipoResponse.GET, "Tipo documento por id", tipoDocumento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarTipoDocumento(@Valid @RequestBody CreateTipoDocumentoDTO tipoDocumentoDTO, @PathVariable Long id){
        ResponseTipoDocumentoDTO tipoDocumento = tipoDocumentoService.updateTipoDocumento(id, tipoDocumentoDTO);
        return ResponseHandler.get(TipoResponse.GET, "Tipo documento actualizado correctamente", tipoDocumento);
    }

}
