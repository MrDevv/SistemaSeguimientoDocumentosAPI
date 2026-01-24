package com.mrdevv.payload.mapper;

import com.mrdevv.model.*;
import com.mrdevv.payload.dto.PageableData;
import com.mrdevv.payload.dto.ResponseWithPageable;
import com.mrdevv.payload.dto.envio.CreateEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDTO;
import com.mrdevv.payload.dto.envio.ResponseEnvioDetalleDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

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

    public static ResponseWithPageable toEnvioDTO(Page<Object[]> envios){
        List<ResponseEnvioDetalleDTO> envioDetalleDTOS = new ArrayList<>();
        PageableData pageableData = PageableMapper.toPageableData(envios);

        envios.stream().forEach(envio -> {
            Long envioId = ((Number) envio[0]).longValue();
            String numeroDocumento =  envio[1].toString();
            String tipoDocumento =  envio[2].toString();
            Integer folios =  ((Number) envio[3]).intValue();
            String indicacion =  envio[4].toString();
            String usuarioOrigen =  envio[5].toString();
            String areaOrigen =  envio[6].toString();
            String usuarioDestino =  envio[7].toString();
            String areaDestino =  envio[8].toString();
            String observacion =  envio[9].toString();
            String fechaEnvio =  envio[10].toString();
            String fechaRecepcion =  envio[11] != null ? envio[11].toString() : null;
            String estadoEnvio =  envio[12].toString();
            String estadoRecepcion =  envio[13].toString();
            String estadoDocumento =  envio[14].toString();

            envioDetalleDTOS.add(new ResponseEnvioDetalleDTO(envioId, numeroDocumento, tipoDocumento,
                    folios, indicacion, usuarioOrigen, areaOrigen, usuarioDestino, areaDestino,
                    observacion, fechaEnvio, fechaRecepcion, estadoEnvio, estadoRecepcion, estadoDocumento));
        });

        return new ResponseWithPageable(envioDetalleDTOS, pageableData);
    }
}
