package org.ahenteti.cli;

import org.ahenteti.cli.api.exception.UserCommandNotFoundException;
import org.ahenteti.cli.api.model.ECommand;
import org.ahenteti.cli.api.service.IPrinter;
import org.ahenteti.cli.api.service.IUserCommandService;
import org.ahenteti.cli.impl.printer.StandardConsolePrinter;
import org.ahenteti.cli.impl.service.command.UserCommandServiceFactory;

public class Main {

    private static IPrinter printer = new StandardConsolePrinter();

    public static void main(String[] args) {
        try {
            ECommand userCommand = getUserCommand(args);
            IUserCommandService service = UserCommandServiceFactory.create(userCommand);
            service.run(args);
        } catch (Exception e) {
            printer.print(e);
        }
    }

    private static ECommand getUserCommand(String[] args) {
        if (args.length == 0) throw new UserCommandNotFoundException();
        return ECommand.from(args[0]);
    }
}
