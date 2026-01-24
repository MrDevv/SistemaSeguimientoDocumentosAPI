package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;
import com.mrdevv.service.IEnvioService;
import com.mrdevv.utils.TipoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final IEnvioService envioService;

    @GetMapping
    public ResponseEntity<Object> listarEnvios(@RequestParam(required = false, name = "numeroDoc") String numDocumento,
                                               @RequestParam(required = false, name = "usuarioArea") Long usuarioAreaId,
                                               @RequestParam(required = false, name = "area") Long areaId,
                                               @RequestParam(required = false, name = "fechaInicio") String fechaInicio,
                                               @RequestParam(required = false, name = "fechaFin") String fechaFin,
                                               @RequestParam(defaultValue = "0") Integer page,
                                               @RequestParam(defaultValue = "10") Integer size){
        ResponseWithPageable envios = envioService.listarEnvios(numDocumento, usuarioAreaId, areaId, fechaInicio, fechaFin, page, size);
        return ResponseHandler.get(TipoResponse.GETALL, "Lista de envíos", envios);
    }

    @PostMapping
    public ResponseEntity<Object> crearEnvio(@Valid @RequestBody CreateEnvioDTO envioDTO){
      ResponseEnvioDTO envio = envioService.saveEnvio(envioDTO);
      return ResponseHandler.get(TipoResponse.CREATE, "Envio registrado correctamente", envio);
    }

    @DeleteMapping("/{idEnvio}")
    public ResponseEntity<Object> cancelarEnvio(@PathVariable Long idEnvio){
        envioService.cancelarEnvio(idEnvio);
        return ResponseHandler.get(TipoResponse.DELETE, "Envio cancelado correctamente", null);
    }
}
