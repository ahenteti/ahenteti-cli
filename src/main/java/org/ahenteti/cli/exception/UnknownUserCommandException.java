package org.ahenteti.cli.exception;

import org.ahenteti.cli.command.ECommand;

public class UnknownUserCommandException extends RuntimeException {
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
