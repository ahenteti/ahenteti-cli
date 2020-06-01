package org.ahenteti.cli.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MultipleChoiceQuestion<T> {

    private String question;
    private ArrayList<QuestionChoice<T>> choices;

    public MultipleChoiceQuestion(String question) {
        this.question = question;
        this.choices = new ArrayList<>();
    }

    public void addChoice(QuestionChoice<T> choice) {
        choices.add(choice);
    }
    
    public String getAuthorizedValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("Authorized values: \n");
        for (QuestionChoice choice : choices) {
            sb.append(" - ");
            sb.append(choice.getShortLabel());
            sb.append(" or ");
            sb.append(choice.getLabel());
            sb.append("\n");
        }
        return sb.toString();
    }
}
