package org.ahenteti.cli.option.service;

import org.ahenteti.cli.option.model.UserSelection;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

public abstract class AbstractUserSelectionService<T> implements IUserSelectionService<T> {

    public T getUserInput() {
        printQuestion();
        printOptions();
        String userInput = getValidUserInput();
        return convert(userInput);
    }

    @Override
    public Optional<UserSelection<T>> findDefaultSelection() {
        return getAuthorizedValues().stream().filter(UserSelection::isDefaultValue).findFirst();
    }

    public boolean isValid(String input) {
        if (StringUtils.isEmpty(input) && findDefaultSelection().isPresent()) {
            return true;
        }
        for (UserSelection<T> authorizedValue : getAuthorizedValues()) {
            if (authorizedValue.getShortSelection().equals(input) || authorizedValue.getLongSelection().equals(input)) {
                return true;
            }
        }
        return false;
    }

    public T convert(String input) {
        if (StringUtils.isEmpty(input)) {
            Optional<UserSelection<T>> defaultSelection = findDefaultSelection();
            if (defaultSelection.isPresent()) {
                return defaultSelection.get().getValue();
            }
        }
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
        StringBuilder sb = new StringBuilder();
        for (UserSelection<T> authorizedValue : getAuthorizedValues()) {
            sb.append(" ");
            sb.append(authorizedValue.getShortSelection());
            sb.append(") ");
            sb.append(authorizedValue.getLongSelection());
            if (authorizedValue.isDefaultValue()) {
                sb.append(" (default value)");
            }
            sb.append("\n");
        }
        sb.append(" Your answer: ");
        System.out.print(sb.toString());
    }


}
