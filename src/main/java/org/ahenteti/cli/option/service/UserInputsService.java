package org.ahenteti.cli.option.service;

import org.ahenteti.cli.exception.UserCommandNotFoundException;
import org.ahenteti.cli.option.model.CommandOptions;
import org.ahenteti.cli.option.model.CommandOptionsFactory;
import org.ahenteti.cli.command.ECommand;

public class UserInputsService {

    public CommandOptions handleUserInputs(String[] args) {
        ECommand command = getUserCommand(args);
        CommandOptions commandOptions = CommandOptionsFactory.create(command);
        if (!commandOptions.isSilent()) {
            ICommandUserInputsService commandUserInputsService = CommandUserInputsServiceFactory.create(command);
            commandOptions = commandUserInputsService.getCommandOptions();
        }
        return commandOptions;
    }

    private ECommand getUserCommand(String[] args) {
        if (args.length == 0) {
            throw new UserCommandNotFoundException();
        }
        return ECommand.from(args[0]);
    }
}
