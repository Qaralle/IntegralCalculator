package main.java.components.inputStrategy;

import main.java.model.FunctionEnvironment;
import main.java.services.SpringScannerProvider;
import main.java.util.RectanglesMethodsName;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class DefaultReader {
    private final SpringScannerProvider scannerProvider;
    private Scanner scanner;

    public DefaultReader(SpringScannerProvider scannerProvider) {
        this.scannerProvider=scannerProvider;
    }

    public FunctionEnvironment readData(InputStream source, FunctionEnvironment data) {

        scanner =  scannerProvider.getScanner(source);

        try {
            switch (data.getMethodService().getMethodName()){
                case SIMPSONA:
                case TRAPEZIUM:
                    if (source == System.in) {
                        System.out.println("enter a:");
                    }
                    data.setA(scanner.nextDouble());
                    if (source == System.in) {
                        System.out.println("enter b:");
                    }
                    data.setB(scanner.nextDouble());
                    if (source == System.in) {
                        System.out.println("enter e:");
                    }
                    data.setE(scanner.nextDouble());
                    break;
                case RECTANGULAR:
                    if (source == System.in) {
                        Arrays.stream(RectanglesMethodsName.values()).forEach(s -> System.out.println(s.toString()));
                        System.out.println("enter method type:");
                    }
                    data.setType(RectanglesMethodsName.valueOf(scanner.nextLine()));
                    if (source == System.in) {
                        System.out.println("enter a:");
                    }
                    data.setA(scanner.nextDouble());
                    if (source == System.in) {
                        System.out.println("enter b:");
                    }
                    data.setB(scanner.nextDouble());
                    if (source == System.in) {
                        System.out.println("enter e:");
                    }
                    data.setE(scanner.nextDouble());
                    break;
            }

            data.setN(4);

        }catch (NoSuchElementException ex){
            throw new CorruptedFileException("Corrupted file");
        }
        return data;
    }
}
