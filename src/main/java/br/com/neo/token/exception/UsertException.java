package br.com.neo.token.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class UsertException extends RuntimeException {
 
    private final String id;
    private final String errorCode;
    private final HttpStatus returnType;
    private final List<String> errors;
 
    public UsertException(String id, String errorCode, HttpStatus returnType, List<String> errors) {
        super();
        this.id = id;
        this.errorCode = errorCode;
        this.returnType = returnType;
        this.errors = errors;
    }

    public String getId() {
        return id;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getReturnType() {
        return returnType;
    }

    public List<String> getErrors() {
        return errors;
    }
}