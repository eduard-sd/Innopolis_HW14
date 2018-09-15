package ru.kuznetsov.exceptions;

public class NoSuchButtonException extends Exception {
    public NoSuchButtonException() {
    }

    public NoSuchButtonException(String message) {
        super(message);
    }

    public NoSuchButtonException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchButtonException(Throwable cause) {
        super(cause);
    }

    public NoSuchButtonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
