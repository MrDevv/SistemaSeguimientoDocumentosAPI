package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import com.mrdevv.service.IUsuarioAreaService;
import com.mrdevv.utils.TipoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
