package org.ahenteti.cli.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Question Option
 *
 * @param <T> option value type
 */

@Data
@AllArgsConstructor
public class QuestionChoice<T> {

    private String shortLabel;
    private String label;
    private T value;

}
