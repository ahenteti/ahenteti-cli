package org.ahenteti.cli.impl.validator;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.ahenteti.cli.api.model.EProjectType;
import org.ahenteti.cli.api.model.MultipleChoiceQuestion;
import org.ahenteti.cli.api.service.IUserSelectionService;
import org.ahenteti.cli.impl.service.user.selection.UserSelectionServiceFactory;

import static org.ahenteti.cli.api.model.CreateProjectCommandOptions.getProjectTypeQuestion;

public class ProjectTypeValidator implements IParameterValidator {

    private IUserSelectionService service;

    public ProjectTypeValidator() {
        service = UserSelectionServiceFactory.create();
    }

    public void validate(String name, String value) {
        MultipleChoiceQuestion<EProjectType> question = getProjectTypeQuestion();
        if (!service.isValid(value, question)) {
            StringBuilder sb = new StringBuilder("Parameter ");
            sb.append(name);
            sb.append(" is not valid.\n");
            sb.append(question.getAuthorizedValues());
            throw new ParameterException(sb.toString());
        }

    }
}
