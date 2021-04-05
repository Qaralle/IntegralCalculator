package main.java.components.command;

import lombok.Getter;
import main.java.model.FunctionEnvironment;
import main.java.services.FunctionService;
import main.java.services.MethodIntegralCalc;
import main.java.util.MethodName;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component

public class InputMapper {
    @Getter
    private Map<Integer, FunctionService> functionServiceMap = new HashMap<>();
    @Getter
    private Map<MethodName, MethodIntegralCalc> methodServiceMap = new HashMap<>();

    private final FunctionService CommonFunctionalService;

    private final MethodIntegralCalc SimpsonMethod;
    private final FunctionService SinusFunction;
    private final FunctionService TestFunction;
    private final FunctionService SecondTestFunction;
    private final FunctionService ThirdTestFunction;

    private final MethodIntegralCalc RectangularMethodService;
    private final MethodIntegralCalc TrapeziumMethodServiсe;

    public InputMapper(FunctionService commonFunctionalService, MethodIntegralCalc simpsonMethod, FunctionService sinusFunction, FunctionService testFunction, FunctionService secondTestFunction, FunctionService thirdTestFunction, MethodIntegralCalc rectangularMethodService, MethodIntegralCalc trapeziumMethodServiсe) {
        this.CommonFunctionalService = commonFunctionalService;
        SimpsonMethod = simpsonMethod;
        SinusFunction = sinusFunction;
        TestFunction = testFunction;
        SecondTestFunction = secondTestFunction;
        ThirdTestFunction = thirdTestFunction;
        RectangularMethodService = rectangularMethodService;
        TrapeziumMethodServiсe = trapeziumMethodServiсe;
    }


    @PostConstruct
    public void __init__(){
        functionServiceMap.put(0, CommonFunctionalService);
        functionServiceMap.put(1, SinusFunction);
        functionServiceMap.put(2, TestFunction);
        functionServiceMap.put(3, SecondTestFunction);
        functionServiceMap.put(4, ThirdTestFunction);

        methodServiceMap.put(MethodName.SIMPSONA, SimpsonMethod);
        methodServiceMap.put(MethodName.RECTANGULAR, RectangularMethodService);
        methodServiceMap.put(MethodName.TRAPEZIUM, TrapeziumMethodServiсe);

    }

    public FunctionEnvironment start(Scanner scanner){
        FunctionEnvironment functionEnvironment = new FunctionEnvironment();

        System.out.println("Выберите желаемую функцию:");
        functionServiceMap.forEach((k, v) -> System.out.println("#" + k + " : " + v.getSignature()));
        if (scanner.hasNextInt()) {
            functionEnvironment.setFunctionService(functionServiceMap.get(scanner.nextInt()));
        }
        System.out.println("Выберите желаемый метод:");
        methodServiceMap.forEach((k, v) -> System.out.println(k + " : " + v.getMethodDescription()));
        if (scanner.hasNext()) {
            String s = scanner.next();
            functionEnvironment.setMethodService(methodServiceMap.get(MethodName.valueOf(s)));
        }

        System.out.print("Ввод данных....\n");

        return functionEnvironment;
    }
}
