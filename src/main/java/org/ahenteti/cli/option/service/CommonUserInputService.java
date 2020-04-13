package org.ahenteti.cli.option.service;

import org.ahenteti.cli.printer.ConsolePrinter;
import org.ahenteti.cli.printer.IPrinter;
import org.ahenteti.cli.reader.ConsoleReader;
import org.ahenteti.cli.reader.IReader;

public class CommonUserInputService {

    protected IReader reader;
    protected IPrinter printer;

    public CommonUserInputService(ConsoleReader reader, IPrinter printer) {
        this.reader = reader;
        this.printer = printer;
    }

    public CommonUserInputService() {
        this(ConsoleReader.getInstance(), ConsolePrinter.getInstance());
    }

}
