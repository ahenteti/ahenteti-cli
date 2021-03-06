package org.ahenteti.cli.service.validator;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class PathValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        try {
            Paths.get(value);
        } catch (InvalidPathException e) {
            throw new ParameterException("Invalid " + name + " parameter: " + e.getMessage());
        }
    }
}
