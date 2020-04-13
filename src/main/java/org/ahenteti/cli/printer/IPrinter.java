package org.ahenteti.cli.printer;

public interface IPrinter {
    void print(String message);

    void printSuccess();

    void print(Exception exception);
    
    void clearLastMessage();
    
    void clearLastMessagePlusReturnKey();
    
    void hideCursor();
    
    void showCursor();
}
