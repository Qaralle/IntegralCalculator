package main.java.services.impl;

import org.springframework.stereotype.Component;
import main.java.services.SpringScannerProvider;


import java.io.InputStream;
import java.util.Scanner;

@Component
public class SpringScannerProviderImpl implements SpringScannerProvider {
    @Override
    public Scanner getScanner(InputStream source) {
        return new Scanner(source);
    }
}