package com.mrdevv.exception;

import lombok.Getter;

public class ObjectNotFoundException extends RuntimeException{

    @Getter
    private final String messageFront;
    private final String messageBack;

    public ObjectNotFoundException(String messageBack, String messageFront) {
        this.messageBack = messageBack;
        this.messageFront = messageFront;
    }

    @Override
    public String getMessage() {
        String message =  super.getMessage();

        if (message == null){
            message = "";
        }

        return message.concat(this.messageBack);
    }
}
