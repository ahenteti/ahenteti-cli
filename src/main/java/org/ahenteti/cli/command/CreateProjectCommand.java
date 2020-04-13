package org.ahenteti.cli.command;

import org.ahenteti.cli.exception.CreateProjectCommandException;
import org.ahenteti.cli.exception.InvalidCommandOptionsException;
import org.ahenteti.cli.option.model.CommandOptions;
import org.ahenteti.cli.option.model.CreateProjectCommandOptions;
import org.ahenteti.cli.util.ProjectVelocityEngine;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CreateProjectCommand implements ICommand {

    @Override
    public void execute(CommandOptions optionsInput) {
        if (!(optionsInput instanceof CreateProjectCommandOptions)) {
            throw new InvalidCommandOptionsException(ECommand.CREATE_PROJECT);
        }
        CreateProjectCommandOptions options = (CreateProjectCommandOptions) optionsInput;
        Path destination = createDestinationDirectory(options);
        unzipProjectFile(options, destination);
        createPomXmlFileFromTemplate(destination, options);
    }

    private void createPomXmlFileFromTemplate(Path destination, CreateProjectCommandOptions options) {
        try (BufferedWriter writer = Files.newBufferedWriter(destination.resolve("pom.xml"))) {
            String pomTemplateFileName = "pom.vm";
            Path pomTemplatePath = destination.resolve(pomTemplateFileName);
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("ctx", options);
            VelocityEngine velocityEngine = new ProjectVelocityEngine(destination);
            velocityEngine.init();
            Template velocityTemplate = velocityEngine.getTemplate(pomTemplateFileName);
            velocityTemplate.merge(velocityContext, writer);
            Files.delete(pomTemplatePath);
        } catch (IOException e) {
            throw new CreateProjectCommandException(e);
        }

    }

    private Path createDestinationDirectory(CreateProjectCommandOptions options) {
        try {
            Path currentUserDir = Paths.get(System.getProperty("user.dir"));
            Path destination = currentUserDir.resolve(options.getProjectName());
            if (destination.toFile().exists()) {
                FileUtils.cleanDirectory(destination.toFile());
            } else {
                Files.createDirectory(destination);
            }
            return destination;
        } catch (IOException e) {
            throw new CreateProjectCommandException(e);
        }
    }

    private void unzipProjectFile(CreateProjectCommandOptions options, Path destination) {
        try (ZipInputStream zis = options.getProjectZipFile()) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String name = zipEntry.getName();
                Path path = destination.resolve(name);
                if (name.endsWith("/")) {
                    Files.createDirectories(path);
                } else {
                    Files.copy(zis, path);
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (IOException e) {
            throw new CreateProjectCommandException(e);
        }
    }

}
