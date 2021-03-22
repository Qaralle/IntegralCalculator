package main.java.components.inputStrategy;

public class CorruptedFileException extends RuntimeException {
    public CorruptedFileException(String text){
        super(text);
    }
}
