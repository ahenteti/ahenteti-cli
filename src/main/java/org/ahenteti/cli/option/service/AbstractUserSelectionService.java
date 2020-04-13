package org.ahenteti.cli.option.service;

import jline.console.Operation;
import org.ahenteti.cli.exception.InvalidUserInputException;
import org.ahenteti.cli.option.model.UserSelection;
import org.ahenteti.cli.util.ConsoleColors;

import java.util.ArrayList;

public abstract class AbstractUserSelectionService<T> extends CommonUserInputService implements IUserSelectionService<T> {

    private int selectedOptionIndex = 0;

    public T getUserInput() {
        printer.print(getQuestionToPrint());
        printer.hideCursor();
        handleUserSelection();
        UserSelection<T> userSelection = getAuthorizedValues().get(selectedOptionIndex);
        printer.clearLastMessage();
        printer.print(getQuestionAndAnswerToPrint(userSelection));
        printer.showCursor();
        return userSelection.getValue();
    }

    public boolean isValid(String input) {
        return getAuthorizedValues().stream().anyMatch(s -> s.getLabel().equals(input));
    }

    public T convert(String input) {
        // @formatter:off
        return getAuthorizedValues()
                .stream()
                .filter(s -> s.getLabel().equals(input))
                .findFirst()
                .orElseThrow(() -> new InvalidUserInputException(input, getAuthorizedValuesMessage()))
                .getValue();
        // @formatter:on
    }

    public String getAuthorizedValuesMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Authorized values: ");
        sb.append(getAuthorizedValues().stream().map(UserSelection::getLabel)
                .reduce((label1, label2) -> label1 + ", " + label2));
        return sb.toString();
    }

    /**
     * handleUserSelection
     * <p>
     * code inspiration:
     * <p>
     * https://github.com/jline/jline2/issues/152 # gnodet commented on Jul 24, 2015
     */
    private void handleUserSelection() {
        while (true) {
            Operation input = reader.readKey();
            int optionsSize = getAuthorizedValues().size();
            if (input == Operation.PREVIOUS_HISTORY) { // UP key
                selectedOptionIndex = (selectedOptionIndex - 1 + optionsSize) % optionsSize;
                reprintQuestion();
            }
            if (input == Operation.NEXT_HISTORY) { // DOWN key
                selectedOptionIndex = (selectedOptionIndex + 1) % optionsSize;
                reprintQuestion();
            }
            if (input == Operation.ACCEPT_LINE) { // RETURN key
                return;
            }
        }
    }

    private void reprintQuestion() {
        printer.clearLastMessage();
        printer.print(getQuestionToPrint());
    }

    protected abstract String getQuestion();

    protected abstract ArrayList<UserSelection<T>> getAuthorizedValues();

    private String getQuestionAndAnswerToPrint(UserSelection<T> userSelection) {
        StringBuilder sb = new StringBuilder();
        sb.append(getQuestionToPrintWithAnswer());
        sb.append(ConsoleColors.ANSI_LIGHT_GREEN);
        sb.append(userSelection.getLabel());
        sb.append(ConsoleColors.ANSI_RESET);
        return sb.toString();
    }

    private String getQuestionToPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append(getQuestionWithoutOptionsToPrint());
        sb.append("\n");
        for (int i = 0; i < getAuthorizedValues().size(); i++) {
            if (selectedOptionIndex == i) {
                sb.append(ConsoleColors.ANSI_GREEN);
                sb.append("> ");
                sb.append(getAuthorizedValues().get(i).getLabel());
                sb.append(ConsoleColors.ANSI_RESET);
            } else {
                sb.append("  ");
                sb.append(getAuthorizedValues().get(i).getLabel());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String getQuestionWithoutOptionsToPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColors.ANSI_CYAN);
        sb.append("? ");
        sb.append(ConsoleColors.ANSI_RESET);
        sb.append(getQuestion());
        sb.append(ConsoleColors.ANSI_WHITE);
        sb.append("(use arrow keys)");
        sb.append(ConsoleColors.ANSI_RESET);
        return sb.toString();
    }

    private String getQuestionToPrintWithAnswer() {
        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColors.ANSI_CYAN);
        sb.append("? ");
        sb.append(ConsoleColors.ANSI_WHITE);
        sb.append(getQuestion());
        sb.append(ConsoleColors.ANSI_RESET);
        return sb.toString();
    }

}
