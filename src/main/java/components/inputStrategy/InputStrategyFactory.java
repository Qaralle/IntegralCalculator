package main.java.components.inputStrategy;

import org.springframework.stereotype.Component;
import main.java.util.StrategyName;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class InputStrategyFactory {
    private Map<StrategyName, InputStrategy> strategies;


    private void createStrategy(Set<InputStrategy> strategySet) {
        strategies = new HashMap<StrategyName, InputStrategy>();
        strategySet.forEach(strategy -> strategies.put(strategy.getStrategyName(), strategy));
    }

    public InputStrategyFactory(Set<InputStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public InputStrategy useIOStrategy(StrategyName strategyName) {
        return strategies.get(strategyName);
    }
}
