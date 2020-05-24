package com.julioluis.trainingrest.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserException extends RuntimeException {

    private String message;

    public UserException() {
    }

    public UserException(String message) {
        super(message);
        this.message=message;
    }
}
