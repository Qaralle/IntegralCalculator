package main.java.components.inputStrategy;

import lombok.Getter;
import main.java.outputStrategy.DefaultWriter;
import main.java.model.FunctionEnvironment;
import org.springframework.stereotype.Component;
import main.java.util.StrategyName;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
@Getter
public class TerminalInputStrategy implements InputStrategy {

    private final DefaultReader defaultReader;

    public TerminalInputStrategy(DefaultReader defaultReader ) throws FileNotFoundException {
        this.defaultReader = defaultReader;
    }

    @Override
    public FunctionEnvironment readData(FunctionEnvironment fe, InputStream source) {
        return defaultReader.readData(source,fe);
    }

    public StrategyName getStrategyName() {
        return StrategyName.TERMINAL;
    }


}
