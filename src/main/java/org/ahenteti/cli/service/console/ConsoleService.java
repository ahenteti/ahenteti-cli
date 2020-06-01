package org.ahenteti.cli.service.console;

import jline.TerminalFactory;
import org.apache.commons.lang3.SystemUtils;

public class ConsoleService implements IConsole {
    @Override
    public boolean isFullFeaturedConsole() {
        return !(SystemUtils.IS_OS_WINDOWS && !TerminalFactory.create().isAnsiSupported());
    }
}
