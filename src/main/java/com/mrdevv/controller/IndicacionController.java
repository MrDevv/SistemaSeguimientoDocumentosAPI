package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.indicacion.CreateIndicacionDTO;
import com.mrdevv.payload.dto.indicacion.ResponseIndicacionDTO;
import com.mrdevv.service.IIndicacionService;
import com.mrdevv.utils.TipoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/indicaciones")
public class IndicacionController {

    private final IIndicacionService indicacionService;

    @Autowired
    public IndicacionController(IIndicacionService indicacionService){
        this.indicacionService = indicacionService;
    }

    @GetMapping
    public ResponseEntity<Object> listarIndicaciones(@RequestParam(required = false) String descripcion){
         List<ResponseIndicacionDTO> indicaciones = indicacionService.getIndicaciones(descripcion);
         return ResponseHandler.get(TipoResponse.GETALL, "listado de indicaciones", indicaciones);
    }

    @PostMapping
    public ResponseEntity<Object> crearIndicacion(@Valid @RequestBody CreateIndicacionDTO indicacionDTO){
        ResponseIndicacionDTO indicacion = indicacionService.saveIndicacion(indicacionDTO);
        return ResponseHandler.get(TipoResponse.CREATE, "Indicacion registrada correctament", indicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarIndicacion(@Valid @RequestBody CreateIndicacionDTO indicacionDTO, @PathVariable Long id){
        ResponseIndicacionDTO indicacion = indicacionService.updateIndicacion(id, indicacionDTO);
        return ResponseHandler.get(TipoResponse.GET, "Indicacion actualizada correctamente", indicacion);
    }
}
