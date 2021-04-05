package main.java.services.impl;

import main.java.services.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class SinusFunction implements FunctionService {
    @Override
    public double f(double x) {
        return Math.sin(x)/x;
    }

    @Override
    public String getSignature() {
        return "sin x/x";
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
        return true;
    }


}
