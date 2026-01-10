package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.service.IRecepcionService;
import com.mrdevv.utils.TipoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recepciones")
public class RecepcionController {

    private final IRecepcionService recepcionService;

    @PatchMapping("{id}/cancelar")
    public ResponseEntity<Object> cancelarRecepcion(@PathVariable(name = "id") Long recepcionId){
        recepcionService.cancelarRecepcion(recepcionId);
        return ResponseHandler.get(TipoResponse.PATCH, "Se canceló la recepción correctamente", null);
    }

    @PatchMapping("{id}/confirmar")
    public ResponseEntity<Object> confirmarRecepcion(@PathVariable(name = "id") Long recepcionId){
        recepcionService.confirmarRecepcion(recepcionId);
        return ResponseHandler.get(TipoResponse.PATCH, "Se confirmó la recepción correctamente", null);
    }

}
