package com.mrdevv.utils;

public enum TipoResponse {
    CREATE(201),
    GET(200),
    GETALL(200),
    UPDATE(200),
    DELETE(204),
    PATCH(200);

    private final Integer status;

    TipoResponse(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }
}
