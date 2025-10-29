package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.area.CreateAreaDTO;
import com.mrdevv.payload.dto.area.ResponseAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaSimpleDTO;
import com.mrdevv.service.IAreaService;
import com.mrdevv.utils.TipoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
@RequiredArgsConstructor
public class AreaController {

    private final IAreaService areaService;

    @GetMapping
    public ResponseEntity<Object> listarAreas(@RequestParam(required = false) Boolean activo, @RequestParam(required = false) String nombre,
                                              @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        ResponseWithPageable areas = areaService.getAreas(activo, nombre, pageable);
        return ResponseHandler.get(TipoResponse.GETALL,"Listado de Areas", areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerAreaById(@PathVariable Long id){
        ResponseAreaDTO area = areaService.getAreaById(id);
        return ResponseHandler.get(TipoResponse.GET,"Datos del area", area);
    }

    @GetMapping("/{id}/usuarios")
    public ResponseEntity<Object> obtenerUsuarioByIdArea(@PathVariable Long id){
        ResponseUsuarioAreaSimpleDTO usuariosPorArea = areaService.getUsuariosActivosPorArea(id);
        return ResponseHandler.get(TipoResponse.GETALL, "Usuarios activos por area", usuariosPorArea);
    }

    @PostMapping
    public ResponseEntity<Object> crearArea(@Valid @RequestBody CreateAreaDTO areaDTO){
       ResponseAreaDTO area = areaService.saveArea(areaDTO);
       return ResponseHandler.get(TipoResponse.CREATE, "Area registrada correctamente", area);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarArea(@PathVariable Long id, @RequestBody CreateAreaDTO areaDTO){
        ResponseAreaDTO area = areaService.updateArea(id, areaDTO);
        return ResponseHandler.get(TipoResponse.UPDATE,"Area actualizada correctamente", area);
    }

    @PatchMapping("/{id}/deshabilitar")
    public ResponseEntity<Object> deshabilitarArea(@PathVariable Long id){
        areaService.disableArea(id);
        return ResponseHandler.get(TipoResponse.PATCH, "Area deshabilitada correctamente", null);
    }

    @PatchMapping("/{id}/habilitar")
    public ResponseEntity<Object> habilitarArea(@PathVariable Long id){
        areaService.enableArea(id);
        return ResponseHandler.get(TipoResponse.PATCH, "Area habilitada correctamente", null);
    }

}
