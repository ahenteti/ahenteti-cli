package org.ahenteti.cli.impl.service.command;

import org.ahenteti.cli.api.model.ConvertPdfToPngCommandOptions;
import org.ahenteti.cli.api.service.IUserCommandService;

public class ConvertPdfToPngCommandService implements IUserCommandService<ConvertPdfToPngCommandOptions> {

    public static final String NOT_IMPLEMENTED_YET = "Not implemented yet";

    @Override
    public ConvertPdfToPngCommandOptions handleUserInputs(String[] args) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    @Override
    public void execute(ConvertPdfToPngCommandOptions options) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }

    @Override
    public void printCompleteMessage() {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED_YET);
    }
}
