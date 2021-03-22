package main.java.components.command;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommandsJavaConfigurer {

    private final TerminalCommand fileIOCommand;
    private final TerminalCommand terminalIOCommand;
    private final TerminalCommand exitCommand;

    private Map<String, TerminalCommand> commandList = new HashMap<>();

    public CommandsJavaConfigurer(FileIOCommand fileIOCommand, TerminalIOCommand terminalIOCommand, TerminalCommand exitCommand) {
        this.fileIOCommand = fileIOCommand;
        this.terminalIOCommand = terminalIOCommand;
        this.exitCommand = exitCommand;
    }

    @PostConstruct
    private void init(){
        commandList.put("file", fileIOCommand);
        commandList.put("terminal", terminalIOCommand);
        commandList.put("exit",exitCommand);
    }

    public Map<String, TerminalCommand> getCommandList(){
        return commandList;
    }

}
