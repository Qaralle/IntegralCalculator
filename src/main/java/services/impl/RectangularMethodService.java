package main.java.services.impl;

import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.FunctionCalculator;
import main.java.services.MethodIntegralCalc;
import main.java.util.MethodName;
import org.springframework.stereotype.Service;

@Service
public class RectangularMethodService implements MethodIntegralCalc {
    private final FunctionCalculator functionCalculator;

    public RectangularMethodService(FunctionCalculator functionCalculator) {
        this.functionCalculator = functionCalculator;
    }

    @Override
    public MethodName getMethodName() {
        return MethodName.RECTANGULAR;
    }

    @Override
    public Double compute(FunctionEnvironment fe, DefaultWriter defaultWriter) {
        double b = fe.getB();
        double a = fe.getA();
        double answ = 0;
        double n = fe.getN();
        double x = 0;


        double step = (b - a) / n;

        switch (fe.getType()){
            case AVG:
                x = a;
                while (x < b){

                    double buff = functionCalculator.calculate(fe, x + (step/2),defaultWriter);
                    if (!Double.isFinite(buff)){
                        return Double.NaN;
                    }

                    answ += buff * step;
                    x += step;
                }

                return answ;
            case LEFT:
                x = a;
                while (x <= b){
                    double buff =functionCalculator.calculate(fe, x ,defaultWriter);
                    if (!Double.isFinite(buff)){
                        return Double.NaN;
                    }
                    answ += buff * step;
                    x += step;
                }

                return answ;
            case RIGHT:
                x = a+step;
                while (x < b){
                    double buff =functionCalculator.calculate(fe, x ,defaultWriter);
                    if (!Double.isFinite(buff)){
                        return Double.NaN;
                    }
                    answ += buff * step;
                    x += step;
                }

                return answ;

        }
        return Double.NaN;
    }

    @Override
    public String getMethodDescription() {
        return "Метод прямоугольников(3 модификации)";
    }
}
