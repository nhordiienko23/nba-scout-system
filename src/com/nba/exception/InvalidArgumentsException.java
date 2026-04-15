package com.nba.exception;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(String message) {
        super(message);
    }
}
