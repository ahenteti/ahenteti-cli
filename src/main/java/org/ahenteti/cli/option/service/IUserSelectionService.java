package org.ahenteti.cli.option.service;

import org.ahenteti.cli.option.model.UserSelection;

import java.util.Optional;

public interface IUserSelectionService<T> {
    T getUserInput();

    Optional<UserSelection<T>> findDefaultSelection();

    boolean isValid(String input);

    T convert(String input);

    String getAuthorizedValuesMessage();
}
