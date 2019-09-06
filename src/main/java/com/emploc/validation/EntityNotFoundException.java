package com.emploc.validation;


import java.io.Serializable;

public class EntityNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1959589679486923774L;
    private String message;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "PersonNotFoundException{" +
                "message='" + message + '\'' +
                '}';
    }
}
