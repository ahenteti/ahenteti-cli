package org.ahenteti.cli.option.service;

import org.ahenteti.cli.exception.UnknownUserCommandException;
import org.ahenteti.cli.command.ECommand;

import java.util.Objects;

public class CommandUserInputsServiceFactory {

    public static ICommandUserInputsService create(ECommand command) {
        switch (command) {
            case CREATE_PROJECT:
                return new CreateProjectCommandUserInputsService();
            case CONVERT_PDF_TO_PNG:
                return new ConvertPdfToPngCommandUserInputsService();
            default:
                throw new UnknownUserCommandException(Objects.toString(command));
        }
    }
}
