package org.ahenteti.cli.api.service;

public interface IUserInputService<T> {

    /**
     * convert user input to its proper type
     *
     * @param input user input
     * @return user input on its proper type
     */
    T convert(String input);

    /**
     * check if the user input is valid
     *
     * @param input user input
     * @return true if the user input is valid
     */
    boolean isValid(String input);

    /**
     * get valid user input
     *
     * @param question question to ask to the user
     * @return valid user input
     */
    T getValidUserInput(String question);
}
