package main.java.components.command;

import main.java.components.inputStrategy.InputStrategyFactory;
import main.java.components.inputStrategy.Validator;
import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.FunctionCalculator;
import main.java.services.IntegralCalculator;
import main.java.util.StrategyName;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Component
public class TerminalIOCommand implements TerminalCommand {

    private final InputStrategyFactory iFactory;
    private final DefaultWriter defaultWriter;
    private final IntegralCalculator integralCalculator;
    private final Validator validator;
    private final int ARGS = 0;

    public TerminalIOCommand(InputStrategyFactory iFactory, DefaultWriter defaultWriter, IntegralCalculator integralCalculator, FunctionCalculator functionCalculator, Validator validator) {
        this.iFactory = iFactory;
        this.defaultWriter = defaultWriter;
        this.integralCalculator = integralCalculator;
        this.validator = validator;
    }

    @Override
    public void execute(FunctionEnvironment fe, String ... args) {

        fe = iFactory.useIOStrategy(StrategyName.FILE).readData(fe,System.in);


        if (args.length==0){
            defaultWriter.setWriter(System.out);
        }else {
            try {
                defaultWriter.setWriter(new FileOutputStream(args[0]));
            } catch (FileNotFoundException e){
                System.out.println("Output file not found.");
            }
        }

        if (validator.validate(fe)){
            if ((fe.getFunctionService().isSymmetrical()) &&
                    (Math.abs(fe.getA()-fe.getFunctionService().symmetricalDot())==Math.abs(fe.getB()-fe.getFunctionService().symmetricalDot()))){

                if (fe.getFunctionService().isEven()) {
                    fe.setA(fe.getFunctionService().symmetricalDot());
                    fe.setEven(true);
                    integralCalculator.iterate(fe, defaultWriter);
                }else System.out.println("I = 0 Нечетная функция на четном интервале, с центров в точке симметрии функции");

            } else integralCalculator.iterate(fe,defaultWriter);
        }


        System.out.println("Вычисления завершены...");
    }

    @Override
    public int getArgs() {
        return this.ARGS;
    }
}
