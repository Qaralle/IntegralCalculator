package main.java.services.impl;

import main.java.services.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class SinusFunction implements FunctionService {
    @Override
    public double f(double x) {
        return Math.sin(x)+0.5;
    }

    @Override
    public String getSignature() {
        return "sin x + 0.5";
    }

    @Override
    public double fSecondDerivative(double x) {
        return -Math.sin(x);
    }

    @Override
    public double fFirstDerivative(double x) {
        return Math.cos(x);
    }
}
