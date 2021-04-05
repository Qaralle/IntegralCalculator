package main.java;

import main.java.components.abstraction.Terminal;
import main.java.components.jfreechart.DrawChart;
import main.java.outputStrategy.DefaultWriter;
import main.java.services.impl.SinusFunction;
import org.springframework.context.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Configuration
@ComponentScan
public class LinearAlgebraicEquationsSolvingConfig {


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LinearAlgebraicEquationsSolvingConfig.class);
        context.getBean(Terminal.class).start();
    }
}
