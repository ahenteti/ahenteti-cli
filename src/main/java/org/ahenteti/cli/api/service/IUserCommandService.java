package org.ahenteti.cli.api.service;

import org.ahenteti.cli.api.model.CommandOptions;

public interface IUserCommandService<T extends CommandOptions> {

    /**
     * gather user command options
     *
     * @param args program arguments
     * @return user command options
     */
    T handleUserInputs(String[] args);

    /**
     * execute user command
     *
     * @param options command option
     */
    void execute(T options);

    /**
     * print complete message
     */
    void printCompleteMessage();

    /**
     * execute the whole workflow:
     * <p>
     * 1. handle user inputs
     * <p>
     * 2. execute user command
     * <p>
     * 3. print complete message
     *
     * @param args program arguments
     */
    default void run(String[] args) {
        T options = handleUserInputs(args);
        execute(options);
        printCompleteMessage();
    }
}
