package main.java.services.impl;

import main.java.services.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class TestFunction implements FunctionService {

    @Override
    public String getSignature() {
        return "3^(x/(1-x^2))";
    }

    @Override
    public boolean isSymmetrical() {
        return false;
    }

    @Override
    public double symmetricalDot() {
        return 0;
    }

    @Override
    public boolean isEven() {
        return false;
    }

    @Override
    public double f(double x) {
        return Math.pow(3,(x/(1-x*x)));
    }

}
