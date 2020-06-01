package org.ahenteti.cli.service.userinput;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IStringConverter;
import org.ahenteti.cli.service.console.ConsoleService;
import org.ahenteti.cli.service.console.IConsole;

public class UserInputServiceFactory<T> {

    private IConsole consoleService;

    public UserInputServiceFactory() {
        this.consoleService = new ConsoleService();
    }

    public AbstractUserInputService<T> create(IStringConverter<T> converter, IParameterValidator validator) {
        if (consoleService.isFullFeaturedConsole()) {
            return new UserInputServiceWorkingOnFullFeaturedConsole<>(converter, validator);
        } else {
            return new UserInputServiceWorkingOnStandardConsole<>(converter, validator);
        }
    }
}
