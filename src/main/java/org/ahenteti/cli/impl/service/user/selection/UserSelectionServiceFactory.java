package org.ahenteti.cli.impl.service.user.selection;

import org.ahenteti.cli.api.service.IConsoleService;
import org.ahenteti.cli.api.service.IUserSelectionService;
import org.ahenteti.cli.impl.service.utils.ConsoleService;

public class UserSelectionServiceFactory {

    private static IConsoleService consoleService = new ConsoleService();

    public static IUserSelectionService create() {
        if (consoleService.isFullFeaturedConsole()) {
            return new UserSelectionServiceWorkingOnFullFeaturedConsole();
        } else {
            return new UserSelectionServiceWorkingOnStandardConsole();
        }
    }
}
