package com.mrdevv.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> get(String mensaje, Object data){
        Map<String, Object> response = new LinkedHashMap();

        response.put("status", HttpStatus.OK);
        response.put("code", HttpStatus.OK.value());
        response.put("message", mensaje);
        response.put("data", data);
        response.put("data_pageable", null);

        return ResponseEntity.ok(response);
    }
}
