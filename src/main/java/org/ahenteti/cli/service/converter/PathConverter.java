package org.ahenteti.cli.service.converter;

import com.beust.jcommander.IStringConverter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathConverter implements IStringConverter<Path> {

    public Path convert(String value) {
        return Paths.get(value);
    }
}
