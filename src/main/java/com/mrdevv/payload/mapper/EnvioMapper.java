package com.mrdevv.payload.mapper;

import com.mrdevv.model.*;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;

public class EnvioMapper {

    public static Envio toEnvioEntity(CreateEnvioDTO envioDTO) {
        return Envio.builder()
                .documento(Documento.builder().id(envioDTO.documentoId()).build())
                .usuarioAreaOrigen(UsuarioArea.builder().id(envioDTO.usuarioAreaOrigenId()).build())
                .usuarioAreaDestino(UsuarioArea.builder().id(envioDTO.usuarioAreaDestinoId()).build())
                .indicacion(Indicacion.builder().id(envioDTO.indicacionId()).build())
                .folios(envioDTO.folios())
                .observacion(envioDTO.observacion())
                .build();
    }

    public static ResponseEnvioDTO toEnvioDTO(Envio envio) {
        return new ResponseEnvioDTO(
                envio.getId(),
                envio.getDocumento().getId(),
                envio.getUsuarioAreaOrigen().getId(),
                envio.getUsuarioAreaDestino().getId(),
                envio.getIndicacion().getId(),
                envio.getFolios(),
                envio.getObservacion(),
                envio.getFechaEnvio()
        );
    }
}
