package org.ahenteti.cli.service.userselection;

import org.ahenteti.cli.service.console.IConsole;
import org.ahenteti.cli.service.console.ConsoleService;

public class UserSelectionServiceFactory {

    private static IConsole consoleService = new ConsoleService();

    public static IUserSelectionService create() {
        if (consoleService.isFullFeaturedConsole()) {
            return new UserSelectionServiceWorkingOnFullFeaturedConsole();
        } else {
            return new UserSelectionServiceWorkingOnStandardConsole();
        }
    }
}
