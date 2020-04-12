package org.ahenteti.cli.exception;

import org.ahenteti.cli.command.ECommand;

public class InvalidCommandOptionsException extends RuntimeException {
    public InvalidCommandOptionsException(ECommand command) {
        super(command + " called with the wrong options");
    }
}
