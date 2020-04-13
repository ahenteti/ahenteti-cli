package org.ahenteti.cli.reader;

import jline.console.Operation;

public interface IReader {

    String readLine();

    Operation readKey();
}
