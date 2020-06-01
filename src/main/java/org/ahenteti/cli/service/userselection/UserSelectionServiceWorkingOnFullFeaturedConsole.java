package org.ahenteti.cli.service.userselection;

import jline.console.Operation;
import org.ahenteti.cli.model.MultipleChoiceQuestion;
import org.ahenteti.cli.model.QuestionChoice;
import org.ahenteti.cli.service.printer.FullFeaturedConsolePrinter;
import org.ahenteti.cli.service.reader.FullFeaturedConsoleReader;
import org.ahenteti.cli.service.shared.ConsoleColors;

public class UserSelectionServiceWorkingOnFullFeaturedConsole extends AbstractUserSelectionService implements IUserSelectionService {

    private int selectedChoiceIndex = 0;
    private final FullFeaturedConsoleReader fullFeaturedConsoleReader;
    private final FullFeaturedConsolePrinter fullFeaturedConsolePrinter;

    public UserSelectionServiceWorkingOnFullFeaturedConsole() {
        super(getReader(), getPrinter());
        this.fullFeaturedConsoleReader = getReader();
        this.fullFeaturedConsolePrinter = getPrinter();
    }

    public <T> void printQuestion(MultipleChoiceQuestion<T> question) {
        fullFeaturedConsolePrinter.print(getQuestionToPrint(question));
        fullFeaturedConsolePrinter.hideCursor();
    }

    private <T> String getQuestionToPrint(MultipleChoiceQuestion<T> question) {
        StringBuilder sb = new StringBuilder();
        sb.append(getQuestionWithoutChoicesToPrint(question));
        sb.append("\n");
        sb.append(getQuestionChoicesToPrint(question));
        return sb.toString();
    }

    private <T> String getQuestionWithoutChoicesToPrint(MultipleChoiceQuestion<T> question) {
        StringBuilder sb = new StringBuilder();
        sb.append(questionService.getQuestionPrefix());
        sb.append(question.getQuestion());
        sb.append(ConsoleColors.ANSI_WHITE);
        sb.append("(use arrow keys)");
        sb.append(ConsoleColors.ANSI_RESET);
        return sb.toString();
    }

    private <T> String getQuestionChoicesToPrint(MultipleChoiceQuestion<T> question) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < question.getChoices().size(); i++) {
            if (selectedChoiceIndex == i) {
                sb.append(ConsoleColors.ANSI_GREEN);
                sb.append("> ");
                sb.append(question.getChoices().get(i).getLabel());
                sb.append(ConsoleColors.ANSI_RESET);
            } else {
                sb.append("  ");
                sb.append(question.getChoices().get(i).getLabel());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public <T> QuestionChoice<T> getUserSelection(MultipleChoiceQuestion<T> question) {
        while (true) {
            Operation input = fullFeaturedConsoleReader.readKey();
            int choicesSize = question.getChoices().size();
            if (input == Operation.PREVIOUS_HISTORY) { // UP key
                selectedChoiceIndex = (selectedChoiceIndex - 1 + choicesSize) % choicesSize;
                reprintQuestion(question);
            }
            if (input == Operation.NEXT_HISTORY) { // DOWN key
                selectedChoiceIndex = (selectedChoiceIndex + 1) % choicesSize;
                reprintQuestion(question);
            }
            if (input == Operation.ACCEPT_LINE) { // RETURN key
                return question.getChoices().get(selectedChoiceIndex);
            }
        }
    }

    private <T> void reprintQuestion(MultipleChoiceQuestion<T> question) {
        clearQuestionFromConsole(question);
        printer.print(getQuestionToPrint(question));
    }

    private <T> void clearQuestionFromConsole(MultipleChoiceQuestion<T> question) {
        fullFeaturedConsolePrinter.clear(1 + question.getChoices().size());
    }

    @Override
    public <T> void printAnswer(MultipleChoiceQuestion<T> question, QuestionChoice<T> userSelection) {
        clearQuestionFromConsole(question);
        fullFeaturedConsolePrinter.print(getQuestionAndAnswerToPrint(question, userSelection));
        fullFeaturedConsolePrinter.showCursor();
    }

    private <T> String getQuestionAndAnswerToPrint(MultipleChoiceQuestion<T> question, QuestionChoice<T> userSelection) {
        StringBuilder sb = new StringBuilder();
        sb.append(getQuestionToPrintWithAnswer(question));
        sb.append(ConsoleColors.ANSI_LIGHT_GREEN);
        sb.append(userSelection.getLabel());
        sb.append(ConsoleColors.ANSI_RESET);
        return sb.toString();
    }

    private <T> String getQuestionToPrintWithAnswer(MultipleChoiceQuestion<T> question) {
        StringBuilder sb = new StringBuilder();
        sb.append(questionService.getQuestionPrefix());
        sb.append(ConsoleColors.ANSI_WHITE);
        sb.append(question.getQuestion());
        sb.append(ConsoleColors.ANSI_RESET);
        return sb.toString();
    }

    private static FullFeaturedConsolePrinter getPrinter() {
        return new FullFeaturedConsolePrinter();
    }

    private static FullFeaturedConsoleReader getReader() {
        return FullFeaturedConsoleReader.getInstance();
    }

}
