package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.areas.ResponseAreaDTO;
import com.mrdevv.service.impl.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private final IAreaService areaService;

    @Autowired
    public AreaController(IAreaService areaService){
        this.areaService = areaService;
    }

    @GetMapping
    public ResponseEntity<Object> listarAreas(@RequestParam(required = false) Boolean activo,
                                              @RequestParam(required = false) String nombre){
        List<ResponseAreaDTO> areas = areaService.getAreas(activo, nombre);
        return ResponseHandler.get("Listado de Areas", areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerAreaById(@PathVariable Long id){
        ResponseAreaDTO area = areaService.getAreaById(id);
        return ResponseHandler.get("Datos del area", area);
    }

}
