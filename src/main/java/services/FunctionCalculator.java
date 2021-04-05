package main.java.services;

import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;

public interface FunctionCalculator {
    Double calculate(FunctionEnvironment fe, Double x, DefaultWriter defaultWriter);
}
