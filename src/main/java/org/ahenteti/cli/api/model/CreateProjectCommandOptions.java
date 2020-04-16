package org.ahenteti.cli.api.model;

import com.beust.jcommander.Parameter;
import lombok.Data;
import org.ahenteti.cli.api.exception.UnknownProjectTypeException;
import org.ahenteti.cli.impl.converter.ProjectTypeConverter;
import org.ahenteti.cli.impl.validator.MandatoryParameterValidator;
import org.ahenteti.cli.impl.validator.ProjectTypeValidator;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Objects;
import java.util.zip.ZipInputStream;

@Data
public class CreateProjectCommandOptions extends CommandOptions {

    @Parameter(names = {"-n", "--name"}, description = "project name", validateWith = MandatoryParameterValidator.class)
    private String name;

    @Parameter(names = {"-t", "--type"}, description = "project type", converter = ProjectTypeConverter.class, validateWith = ProjectTypeValidator.class)
    private EProjectType type = EProjectType.MAVEN;

    public CreateProjectCommandOptions() {
        setCommand(ECommand.CREATE_PROJECT);
    }

    @Override
    public boolean isMissingMandatoryOption() {
        return StringUtils.isBlank(name);
    }

    public static String getProjectNameQuestion() {
        return "Choose a name to your project: ";
    }

    public static MultipleChoiceQuestion<EProjectType> getProjectTypeQuestion() {
        MultipleChoiceQuestion<EProjectType> question = new MultipleChoiceQuestion<>("Which project type would you like to generate? ");
        question.addChoice(new QuestionChoice<>("1", "maven", EProjectType.MAVEN));
        question.addChoice(new QuestionChoice<>("2", "angular", EProjectType.ANGULAR));
        return question;
    }

    public ZipInputStream getProjectZipFile() {
        switch (type) {
            case MAVEN:
                return new ZipInputStream(getResourceStream("create-project-command/maven.zip"));
            case ANGULAR:
                return new ZipInputStream(getResourceStream("create-project-command/angular.zip"));
            default:
                throw new UnknownProjectTypeException(type);
        }
    }

    private InputStream getResourceStream(String resourceName) {
        InputStream resource = getClass().getClassLoader().getResourceAsStream(resourceName);
        Objects.requireNonNull(resource, resourceName + " is not present in the jar");
        return resource;
    }
}
