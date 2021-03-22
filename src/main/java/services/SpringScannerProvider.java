package main.java.services;

import java.io.InputStream;
import java.util.Scanner;

public interface SpringScannerProvider {
    Scanner getScanner(InputStream source);
}