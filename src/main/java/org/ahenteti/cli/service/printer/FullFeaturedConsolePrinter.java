package org.ahenteti.cli.service.printer;

public class FullFeaturedConsolePrinter extends StandardConsolePrinter implements IPrinter {

    public void clear(int nbLines) {
        // code inspiration: https://stackoverflow.com/questions/7522022/how-to-delete-stuff-printed-to-console-by-system-out-println
        for (int i = 0; i < nbLines; i++) {
            System.out.print("\033[2K"); // Erase line content
            System.out.print("\033[1A"); // Move up
        }
    }

    public void hideCursor() {
        // code source: https://rosettacode.org/wiki/Terminal_control/Hiding_the_cursor#Kotlin
        System.out.print("\u001B[?25l");

    }

    public void showCursor() {
        // code source: https://rosettacode.org/wiki/Terminal_control/Hiding_the_cursor#Kotlin
        System.out.print("\u001B[?25h");
    }

}
