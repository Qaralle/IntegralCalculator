package main.java.components.command;

import lombok.Getter;
import main.java.model.FunctionEnvironment;
import main.java.services.ComputationMethodService;
import main.java.services.FunctionService;
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
    private Map<MethodName, ComputationMethodService> methodServiceMap = new HashMap<>();

    private final FunctionService CommonFunctionalService;
    private final ComputationMethodService binarySearchMethodService;
    private final ComputationMethodService SecantMethodService;
    private final ComputationMethodService SimpleIterationService;
    private final FunctionService SinusFunction;
    private final FunctionService TestFunction;


    public InputMapper(FunctionService commonFunctionalService, ComputationMethodService binarySearchMethodService, ComputationMethodService secantMethodService, ComputationMethodService simpleIterationService, FunctionService sinusFunction, FunctionService testFunction) {
        this.CommonFunctionalService = commonFunctionalService;
        this.binarySearchMethodService = binarySearchMethodService;
        SecantMethodService = secantMethodService;
        SimpleIterationService = simpleIterationService;
        SinusFunction = sinusFunction;
        TestFunction = testFunction;
    }


    @PostConstruct
    public void __init__(){
        functionServiceMap.put(0, CommonFunctionalService);
        functionServiceMap.put(1, SinusFunction);
        functionServiceMap.put(2, TestFunction);

        methodServiceMap.put(MethodName.BINSEARCH, binarySearchMethodService);
        methodServiceMap.put(MethodName.SECANTS, SecantMethodService);
        methodServiceMap.put(MethodName.ITERATION, SimpleIterationService);

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
