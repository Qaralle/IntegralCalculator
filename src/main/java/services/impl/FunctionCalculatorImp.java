package main.java.services.impl;

import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.FunctionCalculator;
import main.java.services.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class FunctionCalculatorImp implements FunctionCalculator {
    private final Double DX = 1e-10;
    private final Double MDX = 1e10;

    private  Double functionValueAdvanced(FunctionService fs, double x){
        double value = fs.f(x);
        if (Double.isFinite(value)){
            return  value;
        }else return Double.NaN;
    }


    @Override
    public Double calculate(FunctionEnvironment fe, Double x, DefaultWriter defaultWriter) {
        double value = functionValueAdvanced(fe.getFunctionService(), x);

        if (!Double.isNaN(value)){
            return value;
        }else {
            double left_limit = functionValueAdvanced(fe.getFunctionService(),x - DX);
            double right_limit = functionValueAdvanced(fe.getFunctionService(), x + DX);

            if ((Double.isNaN(left_limit) || Double.isNaN(right_limit)) ||((right_limit <= DX) || (left_limit <= DX))
            ||((right_limit >= MDX) || (left_limit >= MDX))){
                defaultWriter.rawWriteData("Обнаружен разрыв 2-го рода вне точки симметрии");
                return Double.POSITIVE_INFINITY;
            } else {

                if (left_limit == right_limit) {
                    return (left_limit + right_limit) / 2;
                } else {
                    defaultWriter.rawWriteData("Обнаружен неустранимый разрыв 1-го рода");
                    return Double.NaN;
                }

            }
        }

    }
}
