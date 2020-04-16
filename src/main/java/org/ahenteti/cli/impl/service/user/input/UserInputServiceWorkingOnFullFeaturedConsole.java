package org.ahenteti.cli.impl.service.user.input;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.IStringConverter;
import org.ahenteti.cli.api.service.IUserInputService;
import org.ahenteti.cli.impl.printer.FullFeaturedConsolePrinter;
import org.ahenteti.cli.impl.reader.FullFeaturedConsoleReader;
import org.ahenteti.cli.util.ConsoleColors;

public class UserInputServiceWorkingOnFullFeaturedConsole<T> extends AbstractUserInputService<T> implements IUserInputService<T> {

    private FullFeaturedConsolePrinter fullFeaturedConsolePrinter;

    public UserInputServiceWorkingOnFullFeaturedConsole(IStringConverter<T> converter, IParameterValidator validator) {
        super(FullFeaturedConsoleReader.getInstance(), getPrinter(), converter, validator);
        this.fullFeaturedConsolePrinter = getPrinter();
    }

    @Override
    void printTryAgain(String question) {
        clearQuestionFromConsole();
        printQuestion(question);
    }

    @Override
    void printAnswer(String question, String answer) {
        clearQuestionFromConsole();
        StringBuilder sb = new StringBuilder();
        sb.append(questionService.getQuestionPrefix());
        sb.append(ConsoleColors.ANSI_WHITE);
        sb.append(question);
        sb.append(ConsoleColors.ANSI_LIGHT_GREEN);
        sb.append(answer);
        sb.append(ConsoleColors.ANSI_RESET);
        printer.print(sb.toString());
    }

    private static FullFeaturedConsolePrinter getPrinter() {
        return new FullFeaturedConsolePrinter();
    }

    private void clearQuestionFromConsole() {
        // @formatter:off
        fullFeaturedConsolePrinter.clear(1 + 1); // question + one line added when the user press the return key to finalize his input
        // @formatter:on
    }
}
