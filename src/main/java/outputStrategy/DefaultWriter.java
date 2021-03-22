package main.java.outputStrategy;

import main.java.services.SpringWriterProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

@Component
public class DefaultWriter {

    private final SpringWriterProvider springWriterProvider;
    private Writer writer;

    public DefaultWriter(SpringWriterProvider springWriterProvider) {
        this.springWriterProvider=springWriterProvider;
    }

    public void setWriter(OutputStream source){
        writer = springWriterProvider.getWriter(source);
    }

    public <T> Integer writeData(OutputStream source, T ... args){
        setWriter(source);
        return rawWriteData(args);
    }

    public <T> Integer rawWriteData( T ... args){
        try {
            for (T arg : args) {
                writer.write(arg + " ");
            }
            writer.write("\n");
            writer.flush();
            return 1;
        }catch (IOException e){

            return -1;
        }
    }
}
