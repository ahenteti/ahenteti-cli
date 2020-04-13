package org.ahenteti.cli.exception;

public class InvalidUserInputException extends ProjectRuntimeException {
    public InvalidUserInputException(String value, String authorizedValues) {
        super("Invalid input: " + value + ". " + authorizedValues);
    }
}
