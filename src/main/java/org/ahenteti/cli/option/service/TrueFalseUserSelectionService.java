package org.ahenteti.cli.option.service;

import org.ahenteti.cli.option.model.UserSelection;

import java.util.ArrayList;

public class TrueFalseUserSelectionService extends AbstractUserSelectionService<Boolean> {

    private String question;

    public TrueFalseUserSelectionService(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    protected ArrayList<UserSelection<Boolean>> getAuthorizedValues() {
        ArrayList<UserSelection<Boolean>> res = new ArrayList<>();
        res.add(new UserSelection<>("true", Boolean.TRUE));
        res.add(new UserSelection<>("false", Boolean.FALSE));
        return res;
    }

}
