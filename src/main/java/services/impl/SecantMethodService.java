package main.java.services.impl;


import lombok.Getter;
import main.java.components.jfreechart.DrawChart;
import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.ComputationMethodService;
import main.java.util.MethodName;
import org.springframework.stereotype.Service;

@Service
public class SecantMethodService implements ComputationMethodService {

    @Getter
    private final String methodDescription = "Метод секущих";
    @Getter
    private final MethodName methodName = MethodName.SECANTS;
    private final DrawChart drawChart;

    public SecantMethodService(DrawChart drawChart) {
        this.drawChart = drawChart;
    }

    @Override
    public double compute(FunctionEnvironment fe, DefaultWriter defaultWriter) {
        double prev;
        double a = fe.getA();
        double b = fe.getB();
        double e = fe.getE();

        int it = 0;
        double cur = fe.getX1();

        drawChart.draw(a,b, fe.getFunctionService());
        if (fe.getFunctionService().f(b)*fe.getFunctionService().fSecondDerivative(b) > 0){
            prev = b;
        }else prev = a;

        while((Math.abs(fe.getFunctionService().f(cur)) > e) || (Math.abs(cur - prev) > e)){
            double next = cur - ((cur - prev)/(fe.getFunctionService().f(cur) - fe.getFunctionService().f(prev)))* fe.getFunctionService().f(cur);
            defaultWriter.rawWriteData("#",++it," | ","Xk-1 =", prev," | ", "f(Xk-1) =", fe.getFunctionService().f(prev)," | ", "Xk =", cur, " | ","f(Xk) =", fe.getFunctionService().f(cur), " | ","Xk+1 =", next," | ", "f(Xk+1) =", fe.getFunctionService().f(next)," | ","|Xk - Xk+1| =",Math.abs(cur-next));
            prev = cur;
            cur = next;
        }

        return cur;
    }
}
