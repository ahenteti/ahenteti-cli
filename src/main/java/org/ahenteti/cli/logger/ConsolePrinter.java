package org.ahenteti.cli.logger;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.ahenteti.cli.util.ConsoleColors.ANSI_GREEN;
import static org.ahenteti.cli.util.ConsoleColors.ANSI_RED;
import static org.ahenteti.cli.util.ConsoleColors.ANSI_RESET;

public class ConsolePrinter implements IPrinter {

    private static final String INFO_MESSAGE_PREFIX = "[" + ANSI_GREEN + "INFO" + ANSI_RESET + "] ";
    private static final String ERROR_MESSAGE_PREFIX = "[" + ANSI_RED + "ERROR" + ANSI_RESET + "] ";
    private static final ConsolePrinter INSTANCE = new ConsolePrinter(); 
    private int lastPrintedChartersLength;

    private ConsolePrinter() {
    }
    
    public static ConsolePrinter getInstance() {
        return INSTANCE;
    }

    @Override
    public void print(String message) {
        printMessage(message, INFO_MESSAGE_PREFIX);
    }

    @Override
    public void print(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        printMessage(stringWriter.toString(), ERROR_MESSAGE_PREFIX);
    }

    @Override
    public void printNewLine() {
        System.out.println();
    }

    @Override
    public void clearLastMessage() {
        System.out.print(new String(new char[lastPrintedChartersLength]).replace("\0", "\b"));
    }

    private void printMessage(String message, String prefix) {
        message = message.replaceAll("^([^\n])", prefix + "\n$1");
        message = message.replaceAll("[\r\n]*$", "");
        message = message.replaceAll("\n", "\n" + prefix); 
        lastPrintedChartersLength = message.length();
        System.out.print(message);
    }
}
