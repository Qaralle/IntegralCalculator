package main.java.services.impl;

import main.java.services.SpringWriterProvider;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

@Service
public class SpringWriterProviderImpl implements SpringWriterProvider {
    @Override
    public Writer getWriter(OutputStream source) {
        return new OutputStreamWriter(source);
    }
}
