package com.mrdevv.controller;

import com.mrdevv.payload.ResponseHandler;
import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.persona.CreatePersonaDTO;
import com.mrdevv.payload.dto.persona.UpdatePersonaDTO;
import com.mrdevv.payload.dto.usuario.ResponseUsuarioDTO;
import com.mrdevv.payload.dto.usuario_area.CreateUsuarioAreaDTO;
import com.mrdevv.payload.dto.usuario_area.ResponseUsuarioAreaDTO;
import com.mrdevv.service.IUsuarioAreaService;
import com.mrdevv.utils.TipoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioAreaController {

    private final IUsuarioAreaService usuarioAreaService;

    @GetMapping
    public ResponseEntity<Object> getUsuarios(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        ResponseWithPageable<ResponseUsuarioAreaDTO> usuariosArea = usuarioAreaService.getUsuariosArea(pageable);
        return ResponseHandler.get(TipoResponse.GETALL, "Listado de usuarios", usuariosArea);
    }

    @PostMapping
    public ResponseEntity<Object> crearUsuario(@Valid @RequestBody CreateUsuarioAreaDTO usuarioAreaDTO){
        ResponseUsuarioAreaDTO usuarioArea =  usuarioAreaService.saveUsuarioArea(usuarioAreaDTO);
        return ResponseHandler.get(TipoResponse.CREATE, "Usuario registrado correctamente", usuarioArea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarDatosUsuario(@Valid @RequestBody CreatePersonaDTO createPersonaDTO, @PathVariable(name = "id") Long usuarioId){
        ResponseUsuarioDTO usuario = usuarioAreaService.updateUsuario(usuarioId, createPersonaDTO);
        return ResponseHandler.get(TipoResponse.UPDATE, "Usuario actualizado correctamente", usuario);
    }

    @PatchMapping
    public ResponseEntity<Object> actualizarContraseña(){
        return null;
    }

}
