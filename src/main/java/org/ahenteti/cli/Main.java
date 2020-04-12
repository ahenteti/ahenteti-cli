package org.ahenteti.cli;

import org.ahenteti.cli.command.CommandFactory;
import org.ahenteti.cli.command.ICommand;
import org.ahenteti.cli.option.model.CommandOptions;
import org.ahenteti.cli.option.service.UserInputsService;

public class Main {

    public static UserInputsService userInputsService = new UserInputsService();

    public static void main(String[] args) {
        try {
            CommandOptions options = userInputsService.handleUserInputs(args);
            ICommand command = CommandFactory.create(options.getCommand());
            command.execute(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}