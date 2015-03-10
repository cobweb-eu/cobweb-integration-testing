package org.cobweb.web.itests.exceptions;

@SuppressWarnings("serial")
public abstract class BadResponseEx extends Exception {
    public BadResponseEx(String message) {
        super(message);
    }
}

