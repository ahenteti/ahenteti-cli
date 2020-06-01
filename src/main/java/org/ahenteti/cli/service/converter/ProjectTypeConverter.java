package org.ahenteti.cli.service.converter;

import com.beust.jcommander.IStringConverter;
import org.ahenteti.cli.service.userselection.IUserSelectionService;
import org.ahenteti.cli.model.EProjectType;
import org.ahenteti.cli.service.userselection.UserSelectionServiceFactory;

import static org.ahenteti.cli.model.CreateProjectCommandOptions.getProjectTypeQuestion;

public class ProjectTypeConverter implements IStringConverter<EProjectType> {

    private IUserSelectionService userSelectionService;

    public ProjectTypeConverter() {
        userSelectionService = UserSelectionServiceFactory.create();
    }

    public EProjectType convert(String value) {
        return userSelectionService.convert(value, getProjectTypeQuestion()).getValue();
    }
}
