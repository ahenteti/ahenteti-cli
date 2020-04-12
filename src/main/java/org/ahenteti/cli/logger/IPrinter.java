package org.ahenteti.cli.logger;

public interface IPrinter {
    void print(String message);

    void print(Exception exception);
    
    void printNewLine();
    
    void clearLastMessage();
}
