package main.java.services.impl;

import main.java.services.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class ThirdTestFunction implements FunctionService {
    @Override
    public double f(double x) {
        return 1/Math.pow(x,2);
    }

    @Override
    public String getSignature() {
        return "1/x^2";
    }

    @Override
    public boolean isSymmetrical() {
        return true;
    }

    @Override
    public double symmetricalDot() {
        return 0;
    }

    @Override
    public boolean isEven() {
        return true;
    }
}
