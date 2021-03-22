package main.java.components.command;

public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException(String text){
        super(text);
    }
}
