package org.ahenteti.cli.option.service;

import org.ahenteti.cli.option.model.CommandOptions;
import org.ahenteti.cli.option.model.CreateProjectCommandOptions;

public class CreateProjectCommandUserInputsService implements ICommandUserInputsService {

    private ProjectTypeUserSelectionService projectTypeUserSelectionService;
    private MandatoryStringUserInputService mandatoryStringUserInputService;

    public CreateProjectCommandUserInputsService() {
        projectTypeUserSelectionService = new ProjectTypeUserSelectionService();
        mandatoryStringUserInputService = new MandatoryStringUserInputService();
    }

    @Override
    public CommandOptions getCommandOptions() {
        CreateProjectCommandOptions options = new CreateProjectCommandOptions();
        options.setProjectName(mandatoryStringUserInputService.getUserInput("Choose a name for your project: "));
        options.setType(projectTypeUserSelectionService.getUserInput());
        return options;
    }
}
