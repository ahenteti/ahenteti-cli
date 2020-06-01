package org.ahenteti.cli.service.printer;

public interface IPrinter {

    /**
     * print message to the console and prefix the message with new line if it does not already exist
     *
     * @param message message to print
     * @return number of lines added to the console
     */
    int print(String message);

    /**
     * print message to the console
     *
     * @param message message to print
     * @return number of lines added to the console
     */
    int printWithoutNewLinePrefix(String message);

    /**
     * print new line to the console
     */
    void printNewLine();

    /**
     * print exception stacktrace to the console
     *
     * @param exception exception to print
     * @return number of lines added to the console
     */
    int print(Exception exception);
}
