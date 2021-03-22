package main.java.components.inputStrategy;

import main.java.outputStrategy.DefaultWriter;
import main.java.model.FunctionEnvironment;
import main.java.util.StrategyName;

import java.io.InputStream;
import java.io.OutputStream;

public interface InputStrategy {
     FunctionEnvironment readData(FunctionEnvironment fe , InputStream source);
     StrategyName getStrategyName();
}
