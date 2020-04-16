package org.ahenteti.cli.api.model;

import com.beust.jcommander.Parameter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class CommandOptions {

    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = {"-s", "--silent"}, description = "execute the script silently")
    private boolean silent = false;

    @Parameter(names = {"-h", "--help"}, description = "show command usage", help = true)
    private boolean help = false;

    private ECommand command;

    public abstract boolean isMissingMandatoryOption();

}
