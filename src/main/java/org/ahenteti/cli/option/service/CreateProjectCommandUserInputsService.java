package org.ahenteti.cli.option.service;

import org.ahenteti.cli.option.model.CommandOptions;
import org.ahenteti.cli.option.model.CreateProjectCommandOptions;

public class CreateProjectCommandUserInputsService implements ICommandUserInputsService {
    
    private ProjectTypeUserSelectionService projectTypeUserSelectionService;
    private StringUserInputService stringUserInputService;

    public CreateProjectCommandUserInputsService() {
        projectTypeUserSelectionService = new ProjectTypeUserSelectionService();
        stringUserInputService = new StringUserInputService();
    }

    @Override
    public CommandOptions getCommandOptions() {
        CreateProjectCommandOptions options = new CreateProjectCommandOptions();
        options.setProjectName(stringUserInputService.getUserInput("Your project name ? "));
        options.setType(projectTypeUserSelectionService.getUserInput());
        return options;
    }
}
