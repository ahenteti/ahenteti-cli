package org.ahenteti.cli.logger;

import org.ahenteti.cli.util.ConsoleColors;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.stream.LongStream;

import static org.ahenteti.cli.util.ConsoleColors.ANSI_RED;
import static org.ahenteti.cli.util.ConsoleColors.ANSI_RESET;

public class ConsolePrinter implements IPrinter {

    private static final ConsolePrinter INSTANCE = new ConsolePrinter();
    private long lastPrintedMessageLines;

    private ConsolePrinter() {
    }

    public static ConsolePrinter getInstance() {
        return INSTANCE;
    }

    @Override
    public void print(String message) {
        printMessage(ANSI_RESET, message);
    }

    @Override
    public void print(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        printMessage(ANSI_RED, stringWriter.toString());
    }

    /**
     * clear last printed message
     * <p>
     * code inspiration
     * <p>
     * https://stackoverflow.com/questions/7522022/how-to-delete-stuff-printed-to-console-by-system-out-println
     */
    @Override
    public void clearLastMessage() {
        for (int i = 0; i < lastPrintedMessageLines; i++) {
            System.out.print("\033[2K"); // Erase line content
            System.out.print("\033[1A"); // Move up
        }
    }

    private void printMessage(String color, String message) {
        message = message.replaceAll("^([^\n])", "\n$1");
        message = message.replaceAll("[\r\n]*$", "\n");
        message = color + message + ConsoleColors.ANSI_RESET;
        System.out.print(message);
        lastPrintedMessageLines = Arrays.stream(message.split("\n")).count() - 1;
    }
}
