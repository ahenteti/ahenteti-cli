package org.ahenteti.cli.command;

public enum EProjectType {
    MAVEN("maven"), ANGULAR("angular");

    private String value;

    EProjectType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getAuthorizedValues() {
        StringBuilder sb = new StringBuilder("Authorized values: \n");
        for (EProjectType projectType : EProjectType.values()) {
            sb.append(" - ");
            sb.append(projectType.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }
}
