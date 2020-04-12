package org.ahenteti.cli.option.model;

import lombok.Data;
import org.ahenteti.cli.command.ECommand;

@Data
public class ConvertPdfToPngCommandOptions extends CommandOptions {

    public ConvertPdfToPngCommandOptions() {
        this.command = ECommand.CONVERT_PDF_TO_PNG;
    }
}
