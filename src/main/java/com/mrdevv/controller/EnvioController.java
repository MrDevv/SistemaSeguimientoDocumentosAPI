package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;
import com.mrdevv.service.IEnvioService;
import com.mrdevv.utils.TipoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final IEnvioService envioService;

    @Autowired
    public EnvioController(IEnvioService envioService){
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<Object> crearEnvio(@Valid @RequestBody CreateEnvioDTO envioDTO){
      ResponseEnvioDTO envio = envioService.saveEnvio(envioDTO);
      return ResponseHandler.get(TipoResponse.CREATE, "Envio registrado correctamente", envio);
    }
}
