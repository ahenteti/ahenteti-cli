package org.ahenteti.cli.service.reader;

import jline.console.ConsoleReader;
import jline.console.Operation;
import org.ahenteti.cli.exception.ProjectRuntimeException;

import java.io.IOException;

public class FullFeaturedConsoleReader extends ConsoleReader implements IReader {

    private static final FullFeaturedConsoleReader INSTANCE;

    static {
        try {
            INSTANCE = new FullFeaturedConsoleReader();
        } catch (IOException e) {
            throw new ProjectRuntimeException(e);
        }
    }

    public static FullFeaturedConsoleReader getInstance() {
        return INSTANCE;
    }


    private FullFeaturedConsoleReader() throws IOException {
        super();
    }

    @Override
    public String read() {
        try {
            return super.readLine();
        } catch (IOException e) {
            throw new ProjectRuntimeException(e);
        }
    }

    public Operation readKey() {
        try {
            return (Operation) super.readBinding(getKeys());
        } catch (IOException e) {
            throw new ProjectRuntimeException(e);
        }
    }

}
