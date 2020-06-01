package org.ahenteti.cli.service.userselection;

import org.ahenteti.cli.model.MultipleChoiceQuestion;
import org.ahenteti.cli.model.QuestionChoice;

public interface IUserSelectionService {

    /**
     * convert user input to its proper type
     *
     * @param question QCM question
     * @param question question to ask to the user
     * @return user choice
     */
    <T> QuestionChoice<T> convert(String input, MultipleChoiceQuestion<T> question);

    /**
     * check if the user input is valid
     *
     * @param question QCM question
     * @param question question to ask to the user
     * @return true if the user input is valid
     */
    <T> boolean isValid(String input, MultipleChoiceQuestion<T> question);

    /**
     * get valid user input
     *
     * @param question question to ask to the user
     * @return valid user input
     */
    <T> T getValidUserInput(MultipleChoiceQuestion<T> question);
}
