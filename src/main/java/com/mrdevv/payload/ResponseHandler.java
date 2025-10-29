package com.mrdevv.payload;

import com.mrdevv.utils.TipoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> get(TipoResponse tipoResponse, String mensaje, Object data){
        Map<String, Object> response = new LinkedHashMap();

        response.put("status", "Ok");
        response.put("code", tipoResponse.getStatus());
        response.put("message", mensaje);
        response.put("data", data);

        return ResponseEntity.status(tipoResponse.getStatus()).body(response);
    }
}
