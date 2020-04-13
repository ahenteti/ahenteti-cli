package org.ahenteti.cli.option.model;

import com.beust.jcommander.Parameter;
import lombok.Data;
import org.ahenteti.cli.command.ECommand;
import org.ahenteti.cli.command.EProjectType;
import org.ahenteti.cli.exception.UnknownProjectTypeException;
import org.ahenteti.cli.option.converter.ProjectTypeConverter;
import org.ahenteti.cli.option.validator.MandatoryParameterValidator;
import org.ahenteti.cli.option.validator.ProjectTypeValidator;

import java.util.zip.ZipInputStream;

@Data
public class CreateProjectCommandOptions extends CommandOptions {

    @Parameter(names = {"-n", "--name"}, description = "project name", validateWith = MandatoryParameterValidator.class)
    private String projectName;

    @Parameter(names = {"-t", "--type"}, description = "project type", converter = ProjectTypeConverter.class, validateWith = ProjectTypeValidator.class)
    private EProjectType type;

    public CreateProjectCommandOptions() {
        this.command = ECommand.CREATE_PROJECT;
    }

    public ZipInputStream getProjectZipFile() {
        switch (type) {
            case MAVEN:
                return new ZipInputStream(getClass().getClassLoader()
                        .getResourceAsStream("create-project-command/maven.zip"));
            case ANGULAR:
                return new ZipInputStream(getClass().getClassLoader()
                        .getResourceAsStream("create-project-command/angular.zip"));
            default:
                throw new UnknownProjectTypeException(type);
        }
    }
}
