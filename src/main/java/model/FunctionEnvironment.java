package main.java.model;

import lombok.Data;
import main.java.services.FunctionService;
import main.java.services.MethodIntegralCalc;
import main.java.util.RectanglesMethodsName;

@Data
public class FunctionEnvironment {
    private FunctionService functionService;
    private MethodIntegralCalc methodService;
    private Double a;
    private Double b;
    private Double e;
    private Integer n;
    private RectanglesMethodsName type;
    private boolean isEven;

}
