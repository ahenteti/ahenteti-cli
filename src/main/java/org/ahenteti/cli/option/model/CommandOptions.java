package org.ahenteti.cli.option.model;

import com.beust.jcommander.Parameter;
import lombok.Data;
import org.ahenteti.cli.command.ECommand;

@Data
public class CommandOptions {

    @Parameter(names = {"-s", "--silent"}, description = "execute the script silently")
    private boolean silent = false;

    protected ECommand command;

}
