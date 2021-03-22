package main.java.components.inputStrategy;

import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.util.MethodName;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private boolean necessary(FunctionEnvironment functionEnvironment, DefaultWriter defaultWriter){
        if (functionEnvironment.getFunctionService().f(functionEnvironment.getA())* functionEnvironment.getFunctionService().f(functionEnvironment.getB())<0){
            return true;
        } else {
            defaultWriter.rawWriteData("Необходимое условие существования корня уравнения не выполняется");
            return false;
        }
    }

    private boolean sufficient(FunctionEnvironment functionEnvironment, DefaultWriter defaultWriter){

        double len = functionEnvironment.getB()-functionEnvironment.getA();
        double signStart = Math.signum(functionEnvironment.getFunctionService().fFirstDerivative(functionEnvironment.getA()));
        double signMiddle = Math.signum(functionEnvironment.getFunctionService().fFirstDerivative(functionEnvironment.getA()+len/2));
        double signEnd = Math.signum(functionEnvironment.getFunctionService().fFirstDerivative(functionEnvironment.getB()));

        if (signStart == signMiddle && signStart == signEnd) {
            return true;
        }

        defaultWriter.rawWriteData("Достаточное условие единственности корня на отрезке не выполняется");
        return false;
    }

    public boolean validate(FunctionEnvironment functionEnvironment, DefaultWriter defaultWriter){
        if (functionEnvironment.getB()>functionEnvironment.getA()){
            if(functionEnvironment.getMethodService().getMethodName()== MethodName.SECANTS){
                if (functionEnvironment.getX1() < functionEnvironment.getB() && functionEnvironment.getX1() > functionEnvironment.getA()){
                    return sufficient(functionEnvironment,defaultWriter) && necessary(functionEnvironment,defaultWriter);
                }else {
                    defaultWriter.rawWriteData("X1 должно лежать на отрезке от а до b");
                    return false;
                }
            }
            return sufficient(functionEnvironment,defaultWriter) && necessary(functionEnvironment,defaultWriter);
        } else {
            defaultWriter.rawWriteData("а должно быть меньше b");
        }
        return false;
    }


}
