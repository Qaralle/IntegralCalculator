package main.java.components.command;

import lombok.Getter;
import main.java.model.FunctionEnvironment;

import java.io.FileNotFoundException;

public interface TerminalCommand {

      void execute(FunctionEnvironment fe ,String... args) throws FileNotFoundException;

      int getArgs();
}
