package com.karuna.pages.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UnsupportedTypeException extends RuntimeException{

    public UnsupportedTypeException(String exception) {
        super(exception);
    }
}
