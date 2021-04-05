package main.java.services;

import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;

public interface IntegralCalculator {
    double iterate(FunctionEnvironment functionEnvironment, DefaultWriter defaultWriter);
}
