package org.ahenteti.cli.option.service;

import org.ahenteti.cli.option.model.UserSelection;

import java.util.Arrays;
import java.util.Collection;

public class TrueFalseUserSelectionService extends AbstractUserSelectionService<Boolean> {

    private String question;

    public TrueFalseUserSelectionService(String question) {
        this.question = question;
    }

    protected String getQuestion() {
        return question;
    }

    protected Collection<UserSelection<Boolean>> getAuthorizedValues() {
        // @formatter:off
        return Arrays.asList(
            new UserSelection<Boolean>("1", "True", Boolean.TRUE),
            new UserSelection<Boolean>("2", "False", Boolean.FALSE)
        );
        // @formatter:on
    }

}
