package org.ahenteti.cli.option.service;

import org.ahenteti.cli.command.EProjectType;
import org.ahenteti.cli.option.model.UserSelection;

import java.util.ArrayList;

public class ProjectTypeUserSelectionService extends AbstractUserSelectionService<EProjectType> {

    protected String getQuestion() {
        return "Which project type would you like to generate? ";
    }

    protected ArrayList<UserSelection<EProjectType>> getAuthorizedValues() {
        ArrayList<UserSelection<EProjectType>> res = new ArrayList<>();
        res.add(new UserSelection<>("maven", EProjectType.MAVEN));
        res.add(new UserSelection<>("angular", EProjectType.ANGULAR));
        return res;
    }

}
