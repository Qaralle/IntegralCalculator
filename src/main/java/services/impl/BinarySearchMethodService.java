package main.java.services.impl;

import lombok.Getter;
import main.java.components.jfreechart.DrawChart;
import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.ComputationMethodService;
import main.java.services.FunctionService;
import main.java.util.MethodName;
import org.springframework.stereotype.Service;

@Service
public class BinarySearchMethodService implements ComputationMethodService {
    @Getter
    private final MethodName methodName = MethodName.BINSEARCH;
    @Getter
    private final String methodDescription = "Метод половинного деления";

    private final DrawChart drawChart;

    public BinarySearchMethodService(DrawChart drawChart) {
        this.drawChart = drawChart;
    }


    private int sign(double x) {
        if (x > 0)
            return 1;
        else if (x < 0)
            return -1;
        return 0;
    }



    @Override
    public double compute(FunctionEnvironment functionEnvironment, DefaultWriter defaultWriter) {
        double answer = -1;
        double a = functionEnvironment.getA();
        double b = functionEnvironment.getB();
        Double e = functionEnvironment.getE();


        int it = 0;

        FunctionService functionService = functionEnvironment.getFunctionService();

        drawChart.draw(a,b,functionService);
        while (a <= b) {

            double x = (a + b) / 2;


            double test = Math.abs(functionService.f(x));


            if (test <= e) {
                break;
            }

            defaultWriter.rawWriteData("#",++it," | ","a =", a, " | ","b =", b," | ", "x =", x, " | ","f(a) =", functionService.f(a), " | ","f(b) =", functionService.f(b)," | ", "f(x) =", functionService.f(x)," | ","|a-b| =",Math.abs(a-b));

            if (sign(functionService.f(a)) != sign(functionService.f(x))) {
                b = x;
            } else if (sign(functionService.f(x)) != sign(functionService.f(b))) {
                a = x;
            }

        }

        answer = (a + b) / 2;
        defaultWriter.rawWriteData("#",++it," | ","a =", a," | ", "b =", b," | ", "x =", answer, " | ","f(a) =", functionService.f(a), " | ","f(b) =", functionService.f(b)," | ", "f(x) =", functionService.f(answer)," | ","|a-b| =",Math.abs(a-b));

        return answer;
    }
}
