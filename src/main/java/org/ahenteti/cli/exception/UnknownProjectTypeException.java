package org.ahenteti.cli.exception;

import org.ahenteti.cli.model.ECommand;
import org.ahenteti.cli.model.EProjectType;

public class UnknownProjectTypeException extends ProjectRuntimeException {
    public UnknownProjectTypeException(EProjectType projectType) {
        super(calculateExceptionMessage(projectType));
    }

    private static String calculateExceptionMessage(EProjectType projectType) {
        StringBuilder sb = new StringBuilder("unknown project type: ");
        sb.append(projectType);
        sb.append("\n");
        sb.append(ECommand.getAuthorizedValues());
        return sb.toString();
    }
}
