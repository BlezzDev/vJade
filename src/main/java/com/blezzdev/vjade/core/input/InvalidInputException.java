package com.blezzdev.vjade.core.input;

import com.blezzdev.vjade.tools.VJadeException;

public class InvalidInputException extends VJadeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
