package org.ahenteti.cli.impl.service.command;

import org.ahenteti.cli.api.exception.UnknownUserCommandException;
import org.ahenteti.cli.api.model.ECommand;
import org.ahenteti.cli.api.service.IUserCommandService;

public class UserCommandServiceFactory {
    public static IUserCommandService create(ECommand command) {
        switch (command) {
            case CREATE_PROJECT:
                return new CreateProjectCommandService();
            case CONVERT_PDF_TO_PNG:
                return new ConvertPdfToPngCommandService();
            default:
                throw new UnknownUserCommandException(command.name());
        }
    }
}
