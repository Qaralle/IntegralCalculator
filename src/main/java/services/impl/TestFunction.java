package main.java.services.impl;

import main.java.services.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class TestFunction implements FunctionService {

    @Override
    public String getSignature() {
        return "x^3 - x + 4";
    }

    @Override
    public double f(double x) {
        return Math.pow(x, 3) - 3.125*Math.pow(x, 2) - 3.5*x + 2.458;
    }

    @Override
    public double fFirstDerivative(double x) {
        return 3*Math.pow(x, 2) - 6.25*x - 3.5;
    }

    @Override
    public double fSecondDerivative(double x) {
        return 6*x - 6.25;
    }
}
