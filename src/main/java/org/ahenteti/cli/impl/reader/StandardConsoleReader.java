package org.ahenteti.cli.impl.reader;

import org.ahenteti.cli.api.service.IReader;

import java.util.Scanner;

public class StandardConsoleReader implements IReader {

    private final Scanner in = new Scanner(System.in);

    @Override
    public String read() {
        return in.nextLine();
    }

}
