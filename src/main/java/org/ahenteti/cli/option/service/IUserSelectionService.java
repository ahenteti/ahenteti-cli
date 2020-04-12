package org.ahenteti.cli.option.service;

public interface IUserSelectionService<T> {
    T getUserInput();

    boolean isValid(String input);

    T convert(String input);

    String getAuthorizedValuesMessage();
}
