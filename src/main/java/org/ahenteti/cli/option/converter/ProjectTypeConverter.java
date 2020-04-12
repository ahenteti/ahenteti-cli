package org.ahenteti.cli.option.converter;

import com.beust.jcommander.IStringConverter;
import org.ahenteti.cli.command.EProjectType;
import org.ahenteti.cli.option.service.ProjectTypeUserSelectionService;

public class ProjectTypeConverter implements IStringConverter<EProjectType> {
    
    private ProjectTypeUserSelectionService service;

    public ProjectTypeConverter() {
        service = new ProjectTypeUserSelectionService();
    }

    public EProjectType convert(String value) {
        return service.convert(value);
    }
}
