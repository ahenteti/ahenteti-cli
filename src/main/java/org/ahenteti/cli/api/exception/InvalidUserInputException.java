package org.ahenteti.cli.api.exception;

public class InvalidUserInputException extends ProjectRuntimeException {
    public InvalidUserInputException(String input, String authorizedValues) {
        super("Invalid input: " + input + ". " + authorizedValues);
    }
}
