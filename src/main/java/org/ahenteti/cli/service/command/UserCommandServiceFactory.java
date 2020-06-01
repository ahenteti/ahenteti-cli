package org.ahenteti.cli.service.command;

import org.ahenteti.cli.exception.UnknownUserCommandException;
import org.ahenteti.cli.model.ECommand;
import org.ahenteti.cli.service.command.createproject.CreateProjectCommandService;

public class UserCommandServiceFactory {
    public static IUserCommandService create(ECommand command) {
        switch (command) {
            case CREATE_PROJECT:
                return new CreateProjectCommandService();
            default:
                throw new UnknownUserCommandException(command.name());
        }
    }
}
