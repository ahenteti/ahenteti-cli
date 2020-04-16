package org.ahenteti.cli.impl.converter;

import com.beust.jcommander.IStringConverter;
import org.ahenteti.cli.api.model.CreateProjectCommandOptions;
import org.ahenteti.cli.api.service.IUserSelectionService;
import org.ahenteti.cli.api.model.EProjectType;
import org.ahenteti.cli.impl.service.user.selection.UserSelectionServiceFactory;

import static org.ahenteti.cli.api.model.CreateProjectCommandOptions.getProjectTypeQuestion;

public class ProjectTypeConverter implements IStringConverter<EProjectType> {

    private IUserSelectionService userSelectionService;

    public ProjectTypeConverter() {
        userSelectionService = UserSelectionServiceFactory.create();
    }

    public EProjectType convert(String value) {
        return userSelectionService.convert(value, getProjectTypeQuestion()).getValue();
    }
}
