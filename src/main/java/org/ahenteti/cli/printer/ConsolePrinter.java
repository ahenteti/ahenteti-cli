package org.ahenteti.cli.printer;

import org.ahenteti.cli.util.ConsoleColors;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import static org.ahenteti.cli.util.ConsoleColors.ANSI_LIGHT_GREEN;
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
    public void printSuccess() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nTask ");
        sb.append(ANSI_LIGHT_GREEN);
        sb.append("successfully");
        sb.append(ANSI_RESET);
        sb.append(" completed");
        System.out.println(sb.toString());
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

    @Override
    public void clearLastMessagePlusReturnKey() {
        lastPrintedMessageLines++;
        clearLastMessage();
    }

    @Override
    public void hideCursor() {
        // code source: https://rosettacode.org/wiki/Terminal_control/Hiding_the_cursor#Kotlin
        System.out.print("\u001B[?25l");

    }

    @Override
    public void showCursor() {
        // code source: https://rosettacode.org/wiki/Terminal_control/Hiding_the_cursor#Kotlin
        System.out.print("\u001B[?25h");
    }

    private void printMessage(String color, String message) {
        message = message.replaceAll("^([^\n])", "\n$1");
        message = message.replaceAll("[\r\n]*$", "");
        message = color + message + ConsoleColors.ANSI_RESET;
        System.out.print(message);
        lastPrintedMessageLines = getLastPrintedMessageLines(message);
    }

    private long getLastPrintedMessageLines(String message) {
        if (Arrays.stream(message.split("\n")).count() == 1) {
            return 1;
        }
        return Arrays.stream(message.split("\n")).count() - 1;
    }
}
