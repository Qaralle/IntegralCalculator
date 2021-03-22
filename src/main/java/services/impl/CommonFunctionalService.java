package main.java.services.impl;

import main.java.services.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class CommonFunctionalService implements FunctionService {
    public String getSignature(){
        return "-1.38 * x^3 -5.42 *x^2 + 2.57 * x +10.95";
    }

    @Override
    public double f(double x) {
        return -1.38 * Math.pow(x, 3) -5.42 * Math.pow(x, 2) + 2.57 * x +10.95;
    }

    @Override
    public double fSecondDerivative(double x) {
        return -8.28 * x -10.84;
    }

    @Override
    public double fFirstDerivative(double x) {
        return -4.14*Math.pow(x,2)-10.84*x+2.57;
    }
}
