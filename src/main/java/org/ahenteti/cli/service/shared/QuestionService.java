package org.ahenteti.cli.service.shared;

public class QuestionService {
    
    public String getQuestionPrefix() {
        StringBuilder sb = new StringBuilder();
        sb.append(ConsoleColors.ANSI_CYAN);
        sb.append("? ");
        sb.append(ConsoleColors.ANSI_RESET);
        return sb.toString();
    }
    
    public String getPleaseTryAgainQuestion() {
        return "  Invalid value. Please try again: ";
    }
}
