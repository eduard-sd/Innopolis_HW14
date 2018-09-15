package ru.kuznetsov.exceptions;

public class QuantityException extends Exception {
    public QuantityException() {
    }

    public QuantityException(String message) {
        super(message);
    }

    public QuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuantityException(Throwable cause) {
        super(cause);
    }

    public QuantityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
