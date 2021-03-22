package main.java.services;

import main.java.util.MethodName;

public interface FunctionService {
    double f(double x);
    String getSignature();
    double fSecondDerivative(double x);
    double fFirstDerivative(double x);}
