package org.ahenteti.cli.option.model;

import org.ahenteti.cli.command.ECommand;
import org.ahenteti.cli.exception.UnknownUserCommandException;

import java.util.Objects;

public class CommandOptionsFactory {

    public static CommandOptions create(ECommand command) {
        switch (command) {
            case CREATE_PROJECT:
                return new CreateProjectCommandOptions();
            case CONVERT_PDF_TO_PNG:
                return new ConvertPdfToPngCommandOptions();
            default:
                throw new UnknownUserCommandException(Objects.toString(command));
        }
    }
}
