package org.ahenteti.cli.exception;

import org.ahenteti.cli.command.ECommand;

public class UserCommandNotFoundException extends RuntimeException {
    public UserCommandNotFoundException() {
        super("user command is mandatory. " + ECommand.getAuthorizedValues());
    }
}
