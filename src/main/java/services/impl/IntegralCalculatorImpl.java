package main.java.services.impl;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.IntegralCalculator;
import org.springframework.stereotype.Service;

@Service
public class IntegralCalculatorImpl implements IntegralCalculator {


    @Override
    public double iterate(FunctionEnvironment fe, DefaultWriter defaultWriter) {
        int n = fe.isEven()?2:4;

        double diffBuff=Double.MAX_VALUE;
        double buff2 = fe.getMethodService().compute(fe,defaultWriter);
        double last = fe.isEven()?2*buff2:buff2;


        if (Double.isNaN(last) ){
            defaultWriter.rawWriteData("Вычисления остановлены.");
            return Double.NaN;
        }

        defaultWriter.rawWriteData("I =",last,"n = 4");

        double current = 0;
        double diff = 0;
        double buff;


        while(true) {

            n *= 2;
            fe.setN(n);

            buff = fe.getMethodService().compute(fe,defaultWriter);
            current = fe.isEven()?2*buff:buff;



            if (Double.isNaN(current) ){
                defaultWriter.rawWriteData("Вычисления остановлены.");
                return Double.NaN;
            }


            diff = Math.abs(current - last);
            if (diff <= 2 * diffBuff) {
                defaultWriter.rawWriteData("I =",current,"n =",fe.isEven()?2*fe.getN(): fe.getN(),"difI =", diff);
            }else {
                defaultWriter.rawWriteData("Интеграл расходится");
                return Double.NaN;
            }

            diffBuff = diff;

            if (diff<fe.getE()){
                break;
            }
            last =current;
        }

        return current;
    }
}
