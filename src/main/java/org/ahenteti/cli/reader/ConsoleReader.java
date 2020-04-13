package org.ahenteti.cli.reader;

import jline.console.Operation;
import org.ahenteti.cli.exception.ProjectRuntimeException;

import java.io.IOException;

/**
 * project custom ConsoleReader wrapper
 */
public class ConsoleReader extends jline.console.ConsoleReader implements IReader, AutoCloseable {

    private static final ConsoleReader INSTANCE;

    static {
        try {
            INSTANCE = new ConsoleReader();
        } catch (IOException e) {
            throw new ProjectRuntimeException(e);
        }
    }

    public static ConsoleReader getInstance() {
        return INSTANCE;
    }


    private ConsoleReader() throws IOException {
        super();
    }

    @Override
    public String readLine() {
        try {
            return super.readLine();
        } catch (IOException e) {
            throw new ProjectRuntimeException(e);
        }
    }

    @Override
    public Operation readKey() {
        try {
            return (Operation) super.readBinding(getKeys());
        } catch (IOException e) {
            throw new ProjectRuntimeException(e);
        }
    }

}
