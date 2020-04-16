package org.ahenteti.cli.impl.service.utils;

import jline.TerminalFactory;
import org.ahenteti.cli.api.service.IConsoleService;
import org.apache.commons.lang3.SystemUtils;

public class ConsoleService implements IConsoleService {
    @Override
    public boolean isFullFeaturedConsole() {
        return !(SystemUtils.IS_OS_WINDOWS && !TerminalFactory.create().isAnsiSupported());
    }
}
