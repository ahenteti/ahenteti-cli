package org.ahenteti.cli.option.service;

import java.util.Scanner;

public class StringUserInputService {

    public String getUserInput(String question) {
        printQuestion(question);
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private void printQuestion(String question) {
        System.out.print("\n");
        System.out.print(question);
    }

}
