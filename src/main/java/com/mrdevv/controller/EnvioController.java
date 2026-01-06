package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;
import com.mrdevv.service.IEnvioService;
import com.mrdevv.utils.TipoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final IEnvioService envioService;

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
