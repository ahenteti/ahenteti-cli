package org.ahenteti.cli.service.userselection;

import org.ahenteti.cli.model.MultipleChoiceAnswer;
import org.ahenteti.cli.model.MultipleChoiceQuestion;
import org.ahenteti.cli.model.QuestionChoice;
import org.ahenteti.cli.service.printer.IPrinter;
import org.ahenteti.cli.service.reader.IReader;
import org.ahenteti.cli.service.shared.QuestionService;

public abstract class AbstractUserSelectionService implements IUserSelectionService {

    protected final IReader reader;
    protected final IPrinter printer;
    protected final QuestionService questionService;

    public AbstractUserSelectionService(IReader reader, IPrinter printer) {
        this.reader = reader;
        this.printer = printer;
        this.questionService = new QuestionService();
    }

    @Override
    public <T> QuestionChoice<T> convert(String input, MultipleChoiceQuestion<T> question) {
        return MultipleChoiceAnswer.from(input, question).convert();
    }

    @Override
    public <T> boolean isValid(String input, MultipleChoiceQuestion<T> question) {
        return MultipleChoiceAnswer.from(input, question).isValid();
    }

    @Override
    public <T> T getValidUserInput(MultipleChoiceQuestion<T> question) {
        printQuestion(question);
        QuestionChoice<T> userSelection = getUserSelection(question);
        printAnswer(question, userSelection);
        return userSelection.getValue();
    }

    abstract <T> void printQuestion(MultipleChoiceQuestion<T> question);

    abstract <T> QuestionChoice<T> getUserSelection(MultipleChoiceQuestion<T> question);

    abstract <T> void printAnswer(MultipleChoiceQuestion<T> question, QuestionChoice<T> userSelection);

}
