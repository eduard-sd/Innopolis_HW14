package ru.kuznetsov.exceptions;

public class CrumpledMoney extends Exception {
    public CrumpledMoney() {
    }

    public CrumpledMoney(String message) {
        super(message);
    }

    public CrumpledMoney(String message, Throwable cause) {
        super(message, cause);
    }

    public CrumpledMoney(Throwable cause) {
        super(cause);
    }

    public CrumpledMoney(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
