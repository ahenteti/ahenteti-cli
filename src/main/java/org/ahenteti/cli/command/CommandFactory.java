package org.ahenteti.cli.command;

import org.ahenteti.cli.exception.UnknownUserCommandException;

import java.util.Objects;

public class CommandFactory {

    public static ICommand create(ECommand command) {
        switch (command) {
            case CREATE_PROJECT:
                return new CreateProjectCommand();
            case CONVERT_PDF_TO_PNG:
                return new ConvertPdfToPngCommand();
            default:
                throw new UnknownUserCommandException(Objects.toString(command));
        }
    }
}
