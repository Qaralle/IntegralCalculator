package main.java.services.impl;

import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.FunctionCalculator;
import main.java.services.MethodIntegralCalc;
import main.java.util.MethodName;
import org.springframework.stereotype.Service;

@Service
public class SimpsonMethod implements MethodIntegralCalc {
    private final FunctionCalculator functionCalculator;

    public SimpsonMethod(FunctionCalculator functionCalculator) {
        this.functionCalculator = functionCalculator;
    }

    @Override
    public MethodName getMethodName() {
        return MethodName.SIMPSONA;
    }

    @Override
    public Double compute(FunctionEnvironment fe, DefaultWriter defaultWriter) {

        double step = (fe.getB() - fe.getA()) / fe.getN();
        double fa =  functionCalculator.calculate(fe, fe.getA(),defaultWriter);
        double fb = functionCalculator.calculate(fe, fe.getB(),defaultWriter);

        if (!Double.isFinite(fa)  || !Double.isFinite(fb)){
            return Double.NaN;
        }
        double result = fa +  fb;
        double x = fe.getA() + step;
        double i = 1;

        while (x < fe.getB()) {

            double buff = functionCalculator.calculate(fe, x,defaultWriter);

            if (!Double.isFinite(buff)){
                return Double.NaN;
            }

            if (i % 2 != 0){
                result += 4 * buff;
            } else {
                result += 2 * buff;
            }

            x += step;
            i += 1;

        }

        return result * step / 3;
    }

    @Override
    public String getMethodDescription() {
        return "Метод Симпсона";
    }
}
