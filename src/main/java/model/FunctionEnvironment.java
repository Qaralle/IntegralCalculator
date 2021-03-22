package main.java.model;

import lombok.Data;
import main.java.services.ComputationMethodService;
import main.java.services.FunctionService;

@Data
public class FunctionEnvironment {
    private FunctionService functionService;
    private ComputationMethodService methodService;
    private Double a;
    private Double b;
    private Double e;
    private Double x1;
    private Double x0;
}
