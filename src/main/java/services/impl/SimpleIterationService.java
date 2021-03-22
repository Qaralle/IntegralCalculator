package main.java.services.impl;

import lombok.Getter;
import main.java.components.jfreechart.DrawChart;
import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.ComputationMethodService;
import main.java.util.MethodName;
import org.springframework.stereotype.Service;

@Service
public class SimpleIterationService implements ComputationMethodService {

    @Getter
    private final MethodName methodName = MethodName.ITERATION;
    @Getter
    private final String methodDescription = "Метод простых итераций";

    private final DrawChart drawChart;

    public SimpleIterationService(DrawChart drawChart) {
        this.drawChart = drawChart;
    }

    private double countFi(FunctionEnvironment fe,double lambda, double x){
        return x + lambda*fe.getFunctionService().f(x);
    }

    private double countFirstDivFi(FunctionEnvironment fe,double lambda, double x){
        return 1 + lambda*fe.getFunctionService().fFirstDerivative(x);
    }

    private double countQ(double a, double b,FunctionEnvironment fe,double lambda){
        return Math.max(Math.abs(countFirstDivFi(fe,lambda,a)),Math.abs(countFirstDivFi(fe,lambda,b)));
    }

    @Override
    public double compute(FunctionEnvironment fe, DefaultWriter defaultWriter) {
        double a = fe.getA();
        double b = fe.getB();
        double e = fe.getE();

        int it =0;

        double res = a;
        double lambda = -1/Math.max(fe.getFunctionService().fFirstDerivative(a), fe.getFunctionService().fFirstDerivative(b));
        double q = countQ(a,b,fe,lambda);
        double prev;

        drawChart.draw(a,b,fe.getFunctionService());
        if ((q>=1)||(Math.abs(countFirstDivFi(fe,lambda,a))>q)||
                (Math.abs(countFirstDivFi(fe,lambda,b))>q)||
                (Math.abs(countFirstDivFi(fe,lambda,(a+b)/2))>q)) {
            defaultWriter.rawWriteData("Достаточное условие сходимости метода не соблюдается");
            return -1;
        }

        while (true){


            prev = res;
            res = countFi(fe, lambda, prev);

            defaultWriter.rawWriteData("#",++it, " | ", "Xk =", prev, " | ","f(Xk) =", fe.getFunctionService().f(prev),
                    " | ","Xk+1 =", res," | ", "Phi(Xk+1) =", countFi(fe,lambda,res)," | ","F(Xk+1) =", fe.getFunctionService().f(res),
                    " | ","|Xk - Xk+1| =",Math.abs(prev-res));

            defaultWriter.rawWriteData("\n");
            if (0<q && q <=0.5){
                if (Math.abs(res - prev) <= e){
                    break;
                }
            }else if (0.5 < q && q<1){
                if (Math.abs(res - prev) < ((1-q)/q)*e){
                    break;
                }
            }
        }

        return res;
    }

}
