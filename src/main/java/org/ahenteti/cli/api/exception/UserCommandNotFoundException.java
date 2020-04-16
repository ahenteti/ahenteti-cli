package org.ahenteti.cli.api.exception;

import org.ahenteti.cli.api.model.ECommand;

public class UserCommandNotFoundException extends ProjectRuntimeException {
    public UserCommandNotFoundException() {
        super("user command is mandatory. " + ECommand.getAuthorizedValues());
    }
}
