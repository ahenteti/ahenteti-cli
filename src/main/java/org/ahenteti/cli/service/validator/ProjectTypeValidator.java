package org.ahenteti.cli.service.validator;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.ahenteti.cli.model.EProjectType;
import org.ahenteti.cli.model.MultipleChoiceQuestion;
import org.ahenteti.cli.service.userselection.IUserSelectionService;
import org.ahenteti.cli.service.userselection.UserSelectionServiceFactory;

import static org.ahenteti.cli.model.CreateProjectCommandOptions.getProjectTypeQuestion;

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
