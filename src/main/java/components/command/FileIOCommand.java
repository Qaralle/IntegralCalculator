package main.java.components.command;

import main.java.components.inputStrategy.InputStrategyFactory;
import main.java.components.inputStrategy.Validator;
import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.util.StrategyName;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class FileIOCommand implements TerminalCommand {

    private final Validator validator;
    private final InputStrategyFactory ioFactory;
    private final DefaultWriter defaultWriter;
    private final int ARGS = 1;

    public FileIOCommand(Validator validator, InputStrategyFactory ioFactory, DefaultWriter defaultWriter) {
        this.validator = validator;
        this.ioFactory = ioFactory;
        this.defaultWriter = defaultWriter;
    }

    @Override
    public void execute(FunctionEnvironment fe, String ... args) throws FileNotFoundException {
        fe = ioFactory.useIOStrategy(StrategyName.FILE).readData(fe,new FileInputStream(args[0]));

        if (args.length == 2){
            defaultWriter.setWriter(new FileOutputStream(args[1]));
        } else {
            defaultWriter.setWriter(System.out);
        }

        if (validator.validate(fe,defaultWriter)){
            fe.getMethodService().compute(fe, defaultWriter);
        }

        System.out.println("Вычисления завершены...");

    }

    @Override
    public int getArgs() {
        return this.ARGS;
    }
}
