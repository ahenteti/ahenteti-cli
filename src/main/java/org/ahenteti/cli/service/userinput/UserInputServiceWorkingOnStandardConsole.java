package org.ahenteti.cli.service.userinput;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IStringConverter;
import org.ahenteti.cli.service.printer.StandardConsolePrinter;
import org.ahenteti.cli.service.reader.StandardConsoleReader;

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
