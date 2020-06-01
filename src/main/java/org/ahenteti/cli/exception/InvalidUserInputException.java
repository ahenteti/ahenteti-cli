package org.ahenteti.cli.exception;

public class InvalidUserInputException extends ProjectRuntimeException {
    public InvalidUserInputException(String input, String authorizedValues) {
        super("Invalid input: " + input + ". " + authorizedValues);
    }
}
