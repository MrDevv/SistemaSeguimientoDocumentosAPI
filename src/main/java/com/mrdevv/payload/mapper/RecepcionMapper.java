package com.mrdevv.payload.mapper;

import com.mrdevv.model.DocumentoEstado;
import com.mrdevv.model.Envio;
import com.mrdevv.model.Recepcion;
import com.mrdevv.model.UsuarioArea;
import com.mrdevv.payload.dto.recepcion.CreateRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionDTO;
import com.mrdevv.payload.dto.recepcion.ResponseRecepcionEstadoSimpleDTO;

import java.util.List;

public class RecepcionMapper {

    public static Recepcion toRecepcionEntity(CreateRecepcionDTO recepcionDTO, Long idEstadoPendienteRecepcion){
        return Recepcion.builder()
                .envio(Envio.builder().id(recepcionDTO.envioId()).build())
                .usuarioArea(UsuarioArea.builder().id(recepcionDTO.usuarioAreaId()).build())
                .estadoRecepcion(DocumentoEstado.builder().id(idEstadoPendienteRecepcion).build())
                .build();
    }

    public static ResponseRecepcionDTO toRecepcionDTO(Recepcion recepcion){
        return new ResponseRecepcionDTO(
                recepcion.getId(),
                recepcion.getEnvio().getId(),
                recepcion.getUsuarioArea().getId(),
                recepcion.getFechaRecepcion(),
                recepcion.getEstadoRecepcion().getId()
        );
    }

    public static ResponseRecepcionEstadoSimpleDTO toRecepcionEstadoSimpleDTO(List<Object[]> recepcionEstado) {
        Long idRecepcion = null;
        String estadoRecepcion = null;


        for (Object[] result: recepcionEstado){
            idRecepcion = (result[0] != null) ? ((Number) result[0]).longValue() : null;
            estadoRecepcion = (result[1] != null) ? (String) result[1] : null;
        }

        return new ResponseRecepcionEstadoSimpleDTO(idRecepcion, estadoRecepcion);
    }


}
