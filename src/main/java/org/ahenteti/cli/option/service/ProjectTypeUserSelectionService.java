package org.ahenteti.cli.option.service;

import org.ahenteti.cli.command.EProjectType;
import org.ahenteti.cli.option.model.UserSelection;

import java.util.Arrays;
import java.util.Collection;

public class ProjectTypeUserSelectionService extends AbstractUserSelectionService<EProjectType> {

    protected String getQuestion() {
        return "Choose your project type:";
    }

    protected Collection<UserSelection<EProjectType>> getAuthorizedValues() {
        // @formatter:off
        return Arrays.asList(
            new UserSelection<EProjectType>("1", "maven", EProjectType.MAVEN, true),
            new UserSelection<EProjectType>("2", "angular", EProjectType.ANGULAR)
        );
        // @formatter:on
    }

}
