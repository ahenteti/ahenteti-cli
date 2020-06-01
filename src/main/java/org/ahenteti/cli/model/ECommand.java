package org.ahenteti.cli.model;

import org.ahenteti.cli.exception.UnknownUserCommandException;

public enum ECommand {

    CREATE_PROJECT("create-quick-start-project", "create-project", "create");

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
