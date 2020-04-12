package org.ahenteti.cli.option.service;

import org.ahenteti.cli.option.model.UserSelection;

import java.util.Arrays;
import java.util.Collection;

public class TrueFalseUserSelectionService extends AbstractUserSelectionService<Boolean> {

    private String question;
    private boolean defaultValue;

    public TrueFalseUserSelectionService(String question, boolean defaultValue) {
        this.question = question;
        this.defaultValue = defaultValue;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isDefaultValue() {
        return defaultValue;
    }

    protected Collection<UserSelection<Boolean>> getAuthorizedValues() {
        // @formatter:off
        if (defaultValue) {
            return Arrays.asList(
                new UserSelection<>("1", "True", Boolean.TRUE, true),
                new UserSelection<>("2", "False", Boolean.FALSE)
            );
        } else {
            return Arrays.asList(
                new UserSelection<>("1", "True", Boolean.TRUE),
                new UserSelection<>("2", "False", Boolean.FALSE, true)
            );
        }
        // @formatter:on
    }

}
