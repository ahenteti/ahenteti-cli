package org.ahenteti.cli.exception;

import org.ahenteti.cli.model.ECommand;

public class UserCommandNotFoundException extends ProjectRuntimeException {
    public UserCommandNotFoundException() {
        super("user command is mandatory. " + ECommand.getAuthorizedValues());
    }
}
