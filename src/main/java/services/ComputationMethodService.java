package main.java.services;

import main.java.model.FunctionEnvironment;
import main.java.outputStrategy.DefaultWriter;
import main.java.util.MethodName;

public interface ComputationMethodService {
    MethodName getMethodName();
    double compute(FunctionEnvironment fe, DefaultWriter defaultWriter);
    String getMethodDescription();

}
