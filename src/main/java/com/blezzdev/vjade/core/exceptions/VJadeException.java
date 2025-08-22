package com.blezzdev.vjade.core.exceptions;

public class VJadeException extends RuntimeException {
    public VJadeException(String message) {
        super(message);
    }
    public VJadeException(String message, Throwable cause) { super(message, cause); }
}
