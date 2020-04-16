package org.ahenteti.cli.api.exception;

import org.ahenteti.cli.api.model.ECommand;

public class UnknownUserCommandException extends ProjectRuntimeException {
    public UnknownUserCommandException(String command) {
        super(calculateExceptionMessage(command));
    }

    private static String calculateExceptionMessage(String command) {
        StringBuilder sb = new StringBuilder("unknown command: ");
        sb.append(command);
        sb.append("\n");
        sb.append(ECommand.getAuthorizedValues());
        return sb.toString();
    }
}
