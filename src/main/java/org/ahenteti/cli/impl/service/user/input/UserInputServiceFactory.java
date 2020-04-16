package org.ahenteti.cli.impl.service.user.input;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IStringConverter;
import org.ahenteti.cli.api.service.IConsoleService;
import org.ahenteti.cli.impl.service.utils.ConsoleService;

public class UserInputServiceFactory<T> {

    private IConsoleService consoleService;

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
