package main.java.services.impl;

import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.FunctionCalculator;
import main.java.services.MethodIntegralCalc;
import main.java.util.MethodName;
import org.springframework.stereotype.Service;

@Service
public class TrapeziumMethodServiсe implements MethodIntegralCalc {
    private final FunctionCalculator functionCalculator;
    private final Double DX = 1e-10;

    public TrapeziumMethodServiсe(FunctionCalculator functionCalculator) {
        this.functionCalculator = functionCalculator;
    }


    @Override
    public MethodName getMethodName() {
        return MethodName.TRAPEZIUM;
    }

    @Override
    public Double compute(FunctionEnvironment fe, DefaultWriter defaultWriter) {
        double b = fe.getB();
        double a = fe.getA();
        double answ = 0;
        double n = fe.getN();
        double x ;

        double step = (b - a) / n;

        x = a;
        while (x < b){
            double buff = functionCalculator.calculate(fe, x+step,defaultWriter);

            if (!Double.isFinite(buff)){
                return Double.NaN;
            }

            answ += 0.5 * (buff + (functionCalculator.calculate(fe , x,defaultWriter)))* step;
            x += step;
        }

        return answ;
    }

    @Override
    public String getMethodDescription() {
        return "Метод трапеций";
    }
}
