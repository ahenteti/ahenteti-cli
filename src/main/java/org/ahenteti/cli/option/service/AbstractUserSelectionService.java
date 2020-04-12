package org.ahenteti.cli.option.service;

import org.ahenteti.cli.option.model.UserSelection;

import java.util.Collection;
import java.util.Scanner;

public abstract class AbstractUserSelectionService<T> implements IUserSelectionService<T> {

    public T getUserInput() {
        printQuestion();
        printOptions();
        String userInput = getValidUserInput();
        return convert(userInput);
    }

    public boolean isValid(String input) {
        for (UserSelection<T> authorizedValue : getAuthorizedValues()) {
            if (authorizedValue.getShortSelection().equals(input) || authorizedValue.getLongSelection().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public T convert(String input) {
        for (UserSelection<T> authorizedValue : getAuthorizedValues()) {
            if (authorizedValue.getShortSelection().equals(input) || authorizedValue.getLongSelection().equals(input)) {
                return authorizedValue.getValue();
            }
        }
        return null;
    }

    public String getAuthorizedValuesMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Authorized values:\n");
        for (UserSelection<T> authorizedValue : getAuthorizedValues()) {
            sb.append(" - ");
            sb.append(authorizedValue.getShortSelection());
            sb.append(", or");
            sb.append(authorizedValue.getLongSelection());
        }
        return sb.toString();
    }

    private String getValidUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean valid = isValid(input);
        while (!valid) {
            System.out.print(" Invalid value. Please try again: ");
            input = scanner.nextLine();
            valid = isValid(input);
        }
        return input;
    }

    protected abstract String getQuestion();

    protected abstract Collection<UserSelection<T>> getAuthorizedValues();

    private void printQuestion() {
        System.out.print("\n");
        System.out.print(getQuestion());
        System.out.print("\n");
    }

    private void printOptions() {
        for (UserSelection<T> authorizedValue : getAuthorizedValues()) {
            System.out.println(" " + authorizedValue.getShortSelection() + ") " + authorizedValue.getLongSelection());
        }
    }


}
