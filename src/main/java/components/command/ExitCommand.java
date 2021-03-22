package main.java.components.command;

import main.java.model.FunctionEnvironment;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class ExitCommand implements TerminalCommand {
    @Override
    public void execute(FunctionEnvironment fe, String... args) throws FileNotFoundException {
        System.exit(0);
    }

    @Override
    public int getArgs() {
        return 0;
    }
}
