package org.ahenteti.cli.exception;

import java.io.PrintWriter;

public class ProjectRuntimeException extends RuntimeException {

    public ProjectRuntimeException(String message) {
        super(message);
    }

    public ProjectRuntimeException(Throwable cause) {
        super(cause);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        s.println(getMessage());
    }
}
