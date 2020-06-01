package org.ahenteti.cli.service.command.createproject;

import com.beust.jcommander.JCommander;
import org.ahenteti.cli.exception.CreateProjectCommandException;
import org.ahenteti.cli.model.CreateProjectCommandOptions;
import org.ahenteti.cli.service.command.IUserCommandService;
import org.ahenteti.cli.service.command.createproject.CustomVelocityEngine;
import org.ahenteti.cli.service.printer.IPrinter;
import org.ahenteti.cli.service.userselection.IUserSelectionService;
import org.ahenteti.cli.service.printer.StandardConsolePrinter;
import org.ahenteti.cli.service.userinput.MandatoryStringUserInputService;
import org.ahenteti.cli.service.userselection.UserSelectionServiceFactory;
import org.ahenteti.cli.service.shared.ConsoleColors;
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

public class CreateProjectCommandService implements IUserCommandService<CreateProjectCommandOptions> {

    private final IUserSelectionService userSelectionService;
    private final MandatoryStringUserInputService mandatoryStringUserInputService;
    private final IPrinter printer;

    public CreateProjectCommandService() {
        this.userSelectionService = UserSelectionServiceFactory.create();
        this.mandatoryStringUserInputService = new MandatoryStringUserInputService();
        this.printer = new StandardConsolePrinter();
    }

    @Override
    public CreateProjectCommandOptions handleUserInputs(String[] args) {
        CreateProjectCommandOptions options = new CreateProjectCommandOptions();
        JCommander jCommander = JCommander.newBuilder().addObject(options).build();
        jCommander.setProgramName("create-project");
        jCommander.parse(args);
        if (options.isHelp()) {
            jCommander.usage();
            System.exit(0);
        }
        if (options.isMissingMandatoryOption() || !options.isSilent()) {
            // @formatter:off
            options.setType(userSelectionService.getValidUserInput(CreateProjectCommandOptions.getProjectTypeQuestion()));
            options.setName(mandatoryStringUserInputService.getValidUserInput(CreateProjectCommandOptions.getProjectNameQuestion()));
            // @formatter:on
        }
        return options;
    }

    @Override
    public void execute(CreateProjectCommandOptions options) {
        Path destination = createDestinationDirectory(options);
        unzipProjectFile(options, destination);
        createPomXmlFileFromTemplate(destination, options);
    }

    @Override
    public void printCompleteMessage() {
        printer.printNewLine();
        StringBuilder sb = new StringBuilder();
        sb.append("Command executed with ");
        sb.append(ConsoleColors.ANSI_LIGHT_GREEN);
        sb.append("SUCCESS");
        sb.append(ConsoleColors.ANSI_RESET);
        printer.print(sb.toString());
        printer.printNewLine();
    }

    private void createPomXmlFileFromTemplate(Path destination, CreateProjectCommandOptions options) {
        try (BufferedWriter writer = Files.newBufferedWriter(destination.resolve("pom.xml"))) {
            String pomTemplateFileName = "pom.vm";
            Path pomTemplatePath = destination.resolve(pomTemplateFileName);
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("ctx", options);
            VelocityEngine velocityEngine = new CustomVelocityEngine(destination);
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
            Path destination = currentUserDir.resolve(options.getName());
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
