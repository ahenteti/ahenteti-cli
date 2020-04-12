package org.ahenteti.cli.option.validator;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.ahenteti.cli.option.service.ProjectTypeUserSelectionService;

public class ProjectTypeValidator implements IParameterValidator {

    private ProjectTypeUserSelectionService service;

    public ProjectTypeValidator() {
        service = new ProjectTypeUserSelectionService();
    }

    public void validate(String name, String value) {
        if (!service.isValid(value)) {
            StringBuilder sb = new StringBuilder("Parameter ");
            sb.append(name);
            sb.append(" is not valid.\n");
            sb.append(service.getAuthorizedValuesMessage());
            throw new ParameterException(sb.toString());
        }

    }
}
