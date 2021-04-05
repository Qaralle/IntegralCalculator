package main.java.components.inputStrategy;

import main.java.model.FunctionEnvironment;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public boolean validate(FunctionEnvironment functionEnvironment){
        if (functionEnvironment.getA() > functionEnvironment.getB()){
            double swap = functionEnvironment.getA();
            functionEnvironment.setA(functionEnvironment.getB());
            functionEnvironment.setB(swap);
        }
        return true;
    }

}
