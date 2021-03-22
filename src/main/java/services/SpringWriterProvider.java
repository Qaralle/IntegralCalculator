package main.java.services;

import java.io.OutputStream;
import java.io.Writer;

public interface SpringWriterProvider {
    Writer getWriter(OutputStream source);
}
