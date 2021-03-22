package main.java.components.command;

import main.java.model.FunctionEnvironment;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
public class CommandDispatcher {
    private final CommandsJavaConfigurer commandsJavaConfigurer;
    private final InputMapper inputMapper;

    public CommandDispatcher(CommandsJavaConfigurer commandsJavaConfigurer, InputMapper inputMapper, InputMapper inputMapper1) {
        this.commandsJavaConfigurer = commandsJavaConfigurer;
        this.inputMapper = inputMapper1;
    }


    public Scanner dispatch(String mnemonic, Scanner scanner) throws FileNotFoundException {

        String[] splitCommand = mnemonic.split(" ");
        String[] args = new String[splitCommand.length-1];


        if (splitCommand.length>1) {
            System.arraycopy(splitCommand,1,args,0,splitCommand.length-1);
        }

        TerminalCommand currentCommand = commandsJavaConfigurer.getCommandList().get(splitCommand[0]);

        if (currentCommand == null){
            throw new CommandNotFoundException("Unknown command");
        }

        if (splitCommand.length != currentCommand.getArgs()+1 && splitCommand.length != currentCommand.getArgs()+2){
            throw new IllegalArgumentException("Wrong signature of command "+ splitCommand[0]);
        }

        FunctionEnvironment fe = inputMapper.start(scanner);
        commandsJavaConfigurer.getCommandList().get(splitCommand[0]).execute(fe, args);

        return scanner;
    }
}
