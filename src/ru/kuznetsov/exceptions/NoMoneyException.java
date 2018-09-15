package ru.kuznetsov.exceptions;

public class NoMoneyException extends Exception {
    public NoMoneyException() {
    }

    public NoMoneyException(String message) {
        super(message);
    }

    public NoMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoneyException(Throwable cause) {
        super(cause);
    }

    public NoMoneyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
