package org.ahenteti.cli.option.service;

import org.ahenteti.cli.util.ConsoleColors;
import org.apache.commons.lang3.StringUtils;

public class MandatoryStringUserInputService extends CommonUserInputService {

    public String getUserInput(String question) {
        printer.print(getQuestionToPrint(question));
        return getValidUserInput(question);
    }

    private String getValidUserInput(String question) {
        String input = reader.readLine();
        while (StringUtils.isBlank(input)) {
            printer.clearLastMessagePlusReturnKey();
            printer.print(getQuestionToPrint(question));
            input = reader.readLine();
        }
        printer.clearLastMessagePlusReturnKey();
        printer.print(getQuestionAnswerToPrint(question, input));
        return input;
    }

    private String getQuestionAnswerToPrint(String question, String answer) {
        StringBuilder sb = new StringBuilder();
        sb.append(getQuestionToPrintWithAnswer(question));
        sb.append(ConsoleColors.ANSI_LIGHT_GREEN);
        sb.append(answer);
        sb.append(ConsoleColors.ANSI_RESET);
        return sb.toString();
    }

    private String getQuestionToPrint(String question) {
        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColors.ANSI_CYAN);
        sb.append("? ");
        sb.append(ConsoleColors.ANSI_RESET);
        sb.append(question);
        return sb.toString();
    }

    private String getQuestionToPrintWithAnswer(String question) {
        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColors.ANSI_CYAN);
        sb.append("? ");
        sb.append(ConsoleColors.ANSI_WHITE);
        sb.append(question);
        sb.append(ConsoleColors.ANSI_RESET);
        return sb.toString();
    }

}
