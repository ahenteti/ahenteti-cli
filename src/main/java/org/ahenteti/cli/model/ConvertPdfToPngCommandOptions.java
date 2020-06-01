package org.ahenteti.cli.model;

import lombok.Data;

@Data
public class ConvertPdfToPngCommandOptions extends CommandOptions {

    public ConvertPdfToPngCommandOptions() {
        setCommand(ECommand.CONVERT_PDF_TO_PNG);
    }

    @Override
    public boolean isMissingMandatoryOption() {
        return false;
    }
}
