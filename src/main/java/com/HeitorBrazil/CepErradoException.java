package com.HeitorBrazil;

public class CepErradoException extends RuntimeException {
    private final String msg;
    public CepErradoException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}