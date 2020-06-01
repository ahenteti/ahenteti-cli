package org.ahenteti.cli.service.reader;

import java.util.Scanner;

public class StandardConsoleReader implements IReader {

    private final Scanner in = new Scanner(System.in);

    @Override
    public String read() {
        return in.nextLine();
    }

}
