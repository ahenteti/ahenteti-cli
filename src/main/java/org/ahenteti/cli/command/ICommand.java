package org.ahenteti.cli.command;

import org.ahenteti.cli.option.model.CommandOptions;

public interface ICommand {
    void execute(CommandOptions options);
}
