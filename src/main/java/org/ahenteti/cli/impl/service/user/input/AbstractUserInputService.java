package org.ahenteti.cli.impl.service.user.input;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import org.ahenteti.cli.api.service.IPrinter;
import org.ahenteti.cli.api.service.IReader;
import org.ahenteti.cli.api.service.IUserInputService;
import org.ahenteti.cli.impl.service.utils.QuestionService;

public abstract class AbstractUserInputService<T> implements IUserInputService<T> {

    protected final IReader reader;
    protected final IPrinter printer;
    protected final QuestionService questionService;
    private final IStringConverter<T> converter;
    private final IParameterValidator validator;

    public AbstractUserInputService(IReader reader, IPrinter printer, IStringConverter<T> converter, IParameterValidator validator) {
        this.reader = reader;
        this.printer = printer;
        this.converter = converter;
        this.validator = validator;
        this.questionService = new QuestionService();
    }

    @Override
    public T convert(String input) {
        return converter.convert(input);
    }

    @Override
    public boolean isValid(String input) {
        try {
            validator.validate(input, input);
            return true;
        } catch (ParameterException e) {
            return false;
        }
    }

    @Override
    public T getValidUserInput(String question) {
        printQuestion(question);
        String input = reader.read();
        while (!isValid(input)) {
            printTryAgain(question);
            input = reader.read();
        }
        printAnswer(question, input);
        return convert(input);
    }

    protected void printQuestion(String question) {
        printer.print(getQuestionToPrint(question));
    }

    private String getQuestionToPrint(String question) {
        StringBuilder sb = new StringBuilder();
        sb.append(questionService.getQuestionPrefix());
        sb.append(question);
        return sb.toString();
    }

    abstract void printTryAgain(String question);

    abstract void printAnswer(String question, String answer);

}
