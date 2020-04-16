package org.ahenteti.cli.impl.service.user.input;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IStringConverter;
import org.ahenteti.cli.api.service.IUserInputService;
import org.ahenteti.cli.impl.printer.StandardConsolePrinter;
import org.ahenteti.cli.impl.reader.StandardConsoleReader;

public class UserInputServiceWorkingOnStandardConsole<T> extends AbstractUserInputService<T> implements IUserInputService<T> {

    public UserInputServiceWorkingOnStandardConsole(IStringConverter<T> converter, IParameterValidator validator) {
        super(new StandardConsoleReader(), new StandardConsolePrinter(), converter, validator);
    }

    @Override
    void printTryAgain(String question) {
        printer.printWithoutNewLinePrefix(questionService.getPleaseTryAgainQuestion());
    }

    @Override
    void printAnswer(String question, String answer) {
        // no thing to do here
    }
}
