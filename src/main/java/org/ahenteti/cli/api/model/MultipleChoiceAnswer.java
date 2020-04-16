package org.ahenteti.cli.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ahenteti.cli.api.exception.InvalidUserInputException;

import java.util.ArrayList;
import java.util.function.Predicate;

@Data
@AllArgsConstructor
public class MultipleChoiceAnswer<T> {

    private String userInput;
    private MultipleChoiceQuestion<T> question;

    public static <T> MultipleChoiceAnswer<T> from(String answer, MultipleChoiceQuestion<T> question) {
        return new MultipleChoiceAnswer<>(answer, question);
    }

    private ArrayList<QuestionChoice<T>> getQuestionChoices() {
        return question.getChoices();
    }

    public boolean isValid() {
        return getQuestionChoices().stream().anyMatch(questionChoicePredicate());
    }

    public QuestionChoice<T> convert() {
        return getQuestionChoices().stream().filter(questionChoicePredicate()).findFirst()
                .orElseThrow(() -> new InvalidUserInputException(userInput, question.getAuthorizedValues()));
    }

    private Predicate<QuestionChoice<T>> questionChoicePredicate() {
        return c -> c.getLabel().equals(userInput) || c.getShortLabel().equals(userInput);
    }

}
