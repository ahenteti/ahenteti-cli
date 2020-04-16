package org.ahenteti.cli.impl.printer;

import org.ahenteti.cli.api.service.IPrinter;
import org.ahenteti.cli.util.ConsoleColors;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.ahenteti.cli.util.ConsoleColors.ANSI_RED;
import static org.ahenteti.cli.util.ConsoleColors.ANSI_RESET;

public class StandardConsolePrinter implements IPrinter {

    @Override
    public int print(String message) {
        return printMessage(ANSI_RESET, message);
    }

    @Override
    public int printWithoutNewLinePrefix(String message) {
        return printMessage(ANSI_RESET, message, "");
    }

    @Override
    public void printNewLine() {
        System.out.println();
    }

    @Override
    public int print(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        return printMessage(ANSI_RED, stringWriter.toString());
    }

    private int printMessage(String color, String message) {
        return printMessage(color, message, "\n");
    }

    private int printMessage(String color, String message, String messagePrefix) {
        message = message.replaceAll("^([^\n])", messagePrefix + "$1");
        message = message.replaceAll("[\r\n]*$", "");
        message = color + message + ConsoleColors.ANSI_RESET;
        System.out.print(message);
        return message.split("\n").length - 1;
    }

}
