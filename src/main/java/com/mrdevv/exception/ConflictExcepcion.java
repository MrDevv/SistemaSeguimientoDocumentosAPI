package com.mrdevv.exception;

import lombok.Getter;

public class ConflictExcepcion extends RuntimeException{

    @Getter
    private final String messageFront;
    private final String messageBack;

    public ConflictExcepcion(String messageBack, String messageFront){
        this.messageBack = messageBack;
        this.messageFront = messageFront;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();

        if (message == null){
            message = "";
        }

        return message.concat(messageBack);
    }

}
