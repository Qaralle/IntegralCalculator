package main.java.components;

import main.java.components.abstraction.Terminal;
import main.java.components.command.CommandDispatcher;
import main.java.components.command.CommandNotFoundException;
import main.java.components.command.InputMapper;
import main.java.components.inputStrategy.CorruptedFileException;
import main.java.model.FunctionEnvironment;
import main.java.services.SpringScannerProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.Scanner;


@Component
public class SimpleApplicationTerminal implements Terminal {

    private final SpringScannerProvider scannerProvider;
    private final CommandDispatcher commandDispatcher;
    private Scanner scanner;

    public SimpleApplicationTerminal(SpringScannerProvider scannerProvider, CommandDispatcher commandDispatcher){
        this.scannerProvider = scannerProvider;
        this.commandDispatcher = commandDispatcher;
    }

    @PostConstruct
    public void __init__(){
        scanner = scannerProvider.getScanner(System.in);
    }

    @Override
    public void start(){
        System.out.println("Для того чтобы воспользоваться программой\nвыберите формат ввода значений\n" +
                "\nварианты команд:\n>>>file [path_in] ||optional|| [path_out]\n>>>terminal ||optional|| [path_out] [.......]\n"+
                "для выхода введите:: exit");
        while (true) {
            if (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!s.equals("")) {
                    try {

                        scanner = commandDispatcher.dispatch(s, scanner);
                    } catch (CommandNotFoundException | FileNotFoundException | CorruptedFileException | IllegalArgumentException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }

        }
    }
}
