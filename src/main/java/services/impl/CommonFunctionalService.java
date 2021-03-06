package main.java.services.impl;

import main.java.services.FunctionService;
import org.springframework.stereotype.Service;

@Service
public class CommonFunctionalService implements FunctionService {
    public String getSignature(){
        return "-3 * x^3 -5 * x^2 + 4 * x -2";
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
        return -3 * Math.pow(x, 3) -5 * Math.pow(x, 2) + 4 * x -2;
    }


}
