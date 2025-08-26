package com.blezzdev.vjade.tools;

public class VJadeException extends RuntimeException {
    public VJadeException(String message) {
        super(message);
    }
    public VJadeException(String message, Throwable cause) { super(message, cause); }
}
