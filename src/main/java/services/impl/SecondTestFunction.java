package main.java.services.impl;

import main.java.services.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class SecondTestFunction implements FunctionService {
    @Override
    public String getSignature() {
        return "1/x";
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
        return false;
    }

    @Override
    public double f(double x) {
        return 1/x;
    }
}
