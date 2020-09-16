package com.epam.dfilatov.istore.exception;

public class ValidationException extends IllegalArgumentException {

    private static final long serialVersionUID = -1882348016166728296L;

    public ValidationException(String s) {
        super(s);
    }
}
