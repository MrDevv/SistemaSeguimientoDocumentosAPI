package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.usuario_area.CreateUsuarioAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import com.mrdevv.service.IUsuarioAreaService;
import com.mrdevv.utils.TipoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioAreaController {

    private final IUsuarioAreaService usuarioAreaService;

    @Autowired
    public UsuarioAreaController(IUsuarioAreaService usuarioAreaService){
        this.usuarioAreaService = usuarioAreaService;
    }

    @GetMapping
    public ResponseEntity<Object> getUsuarios(){
        List<ResponseUsuarioAreaDTO> usuariosArea = usuarioAreaService.getUsuariosArea();
        return ResponseHandler.get(TipoResponse.GETALL, "Listado de usuarios", usuariosArea);
    }

    @PostMapping
    public ResponseEntity<Object> crearUsuario(@Valid @RequestBody CreateUsuarioAreaDTO usuarioAreaDTO){
        ResponseUsuarioAreaDTO usuarioArea =  usuarioAreaService.saveUsuarioArea(usuarioAreaDTO);
        return ResponseHandler.get(TipoResponse.CREATE, "Usuario registrado correctamente", usuarioArea);
    }

}
