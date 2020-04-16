package org.ahenteti.cli.impl.service.user.selection;

import org.ahenteti.cli.api.model.MultipleChoiceQuestion;
import org.ahenteti.cli.api.model.QuestionChoice;
import org.ahenteti.cli.api.service.IUserSelectionService;
import org.ahenteti.cli.impl.printer.StandardConsolePrinter;
import org.ahenteti.cli.impl.reader.StandardConsoleReader;

public class UserSelectionServiceWorkingOnStandardConsole extends AbstractUserSelectionService implements IUserSelectionService {


    public UserSelectionServiceWorkingOnStandardConsole() {
        super(new StandardConsoleReader(), new StandardConsolePrinter());
    }

    public <T> void printQuestion(MultipleChoiceQuestion<T> question) {
        printer.print(getQuestionToPrint(question));
    }

    private <T> String getQuestionToPrint(MultipleChoiceQuestion<T> question) {
        StringBuilder sb = new StringBuilder();
        sb.append(getQuestionWithoutChoicesToPrint(question));
        sb.append("\n");
        sb.append(getQuestionChoicesToPrint(question));
        sb.append("  Your answer: ");
        return sb.toString();
    }

    private <T> String getQuestionWithoutChoicesToPrint(MultipleChoiceQuestion<T> question) {
        StringBuilder sb = new StringBuilder();
        sb.append(questionService.getQuestionPrefix());
        sb.append(question.getQuestion());
        return sb.toString();
    }

    private <T> String getQuestionChoicesToPrint(MultipleChoiceQuestion<T> question) {
        StringBuilder sb = new StringBuilder();
        for (QuestionChoice<T> choice : question.getChoices()) {
            sb.append("  ");
            sb.append(choice.getShortLabel());
            sb.append(") ");
            sb.append(choice.getLabel());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public <T> QuestionChoice<T> getUserSelection(MultipleChoiceQuestion<T> question) {
        String input = reader.read();
        boolean valid = isValid(input, question);
        while (!valid) {
            printer.printWithoutNewLinePrefix(questionService.getPleaseTryAgainQuestion());
            input = reader.read();
            valid = isValid(input, question);
        }
        return convert(input, question);
    }

    @Override
    public <T> void printAnswer(MultipleChoiceQuestion<T> question, QuestionChoice<T> userSelection) {
        // no thing to do here
    }
}
