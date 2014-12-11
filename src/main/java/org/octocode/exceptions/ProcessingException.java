package org.octocode.exceptions;

public class ProcessingException extends RuntimeException {
    public ProcessingException(String message, Exception exception) {
        super(message, exception);
    }
}
