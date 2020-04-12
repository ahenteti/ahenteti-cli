package org.ahenteti.cli.option.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * User Selection
 *
 * @param <T> user selection type
 */

@Data
@AllArgsConstructor
public class UserSelection<T> {

    private String shortSelection;
    private String longSelection;
    private T value;
    private boolean defaultValue;

    public UserSelection(String shortSelection, String longSelection, T value) {
        this(shortSelection, longSelection, value, false);
    }
}
