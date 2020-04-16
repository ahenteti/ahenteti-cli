package org.ahenteti.cli.impl.service.user.selection;

import org.ahenteti.cli.api.model.MultipleChoiceAnswer;
import org.ahenteti.cli.api.model.MultipleChoiceQuestion;
import org.ahenteti.cli.api.model.QuestionChoice;
import org.ahenteti.cli.api.service.IPrinter;
import org.ahenteti.cli.api.service.IReader;
import org.ahenteti.cli.api.service.IUserSelectionService;
import org.ahenteti.cli.impl.service.utils.QuestionService;

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
