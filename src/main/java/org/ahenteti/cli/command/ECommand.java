package org.ahenteti.cli.command;

import org.ahenteti.cli.exception.UnknownUserCommandException;

public enum ECommand {

    // @formatter:off
    CREATE_PROJECT("create-quick-start-project", "create-project", "create"),
    CONVERT_PDF_TO_PNG("convert-pdf-to-png", "pdf-to-png", "p2p");
    // @formatter:on

    private String[] values;

    ECommand(String... values) {
        this.values = values;
    }

    public String[] getValues() {
        return values;
    }

    public static ECommand from(String commandString) {
        for (ECommand command : ECommand.values()) {
            for (String value : command.getValues()) {
                if (value.equals(commandString)) {
                    return command;
                }
            }
        }
        throw new UnknownUserCommandException(commandString);
    }

    public static String getAuthorizedValues() {
        StringBuilder sb = new StringBuilder("Authorized values: \n");
        for (ECommand command : ECommand.values()) {
            boolean first = true;
            for (String value : command.getValues()) {
                if (first) {
                    sb.append(" - ");
                    sb.append(value);
                    first = false;
                } else {
                    sb.append(" or ");
                    sb.append(value);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
