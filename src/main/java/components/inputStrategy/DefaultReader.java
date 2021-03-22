package main.java.components.inputStrategy;

import main.java.model.FunctionEnvironment;
import main.java.services.SpringScannerProvider;
import org.springframework.stereotype.Component;

import java.io.InputStream;
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
                case BINSEARCH:
                case ITERATION:
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
                case SECANTS:
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
                    if (source == System.in) {
                        System.out.println("enter x1:");
                    }
                    data.setX1(scanner.nextDouble());
                    break;

            }
        }catch (NoSuchElementException ex){
            throw new CorruptedFileException("Corrupted file");
        }
        return data;
    }
}
