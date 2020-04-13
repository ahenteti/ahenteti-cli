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

    private String label;
    private T value;

}
