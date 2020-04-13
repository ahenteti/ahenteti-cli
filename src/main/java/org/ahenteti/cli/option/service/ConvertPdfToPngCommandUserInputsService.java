package org.ahenteti.cli.option.service;

import org.ahenteti.cli.option.model.CommandOptions;

public class ConvertPdfToPngCommandUserInputsService implements ICommandUserInputsService {

    @Override
    public CommandOptions getCommandOptions() {
        return new CommandOptions();
    }

}
