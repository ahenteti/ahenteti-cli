package org.ahenteti.cli.option.service;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class MandatoryStringUserInputService {

    public String getUserInput(String question) {
        printQuestion(question);
        return getValidUserInput();
    }

    private String getValidUserInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (StringUtils.isBlank(input)) {
            System.out.print(" Empty string is invalid. Please try again: ");
            input = in.nextLine();
        }
        return input;
    }

    private void printQuestion(String question) {
        System.out.print("\n");
        System.out.print(question);
    }

}
