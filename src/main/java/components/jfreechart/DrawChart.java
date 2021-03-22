package main.java.components.jfreechart;

import main.java.services.FunctionService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class DrawChart {

    private void rawDraw(XYSeriesCollection lineDataset, FunctionService fservice) {


        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "f(x)", "x",
                "y",
                lineDataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1920;
        int height = 1080;






        try {
            ChartUtils.saveChartAsJPEG(new File("src/main/resources/ans.jpeg"), lineChart, width, height);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    public void drawForIteration(double a, double b, double lambda, FunctionService fservice) {
        XYSeriesCollection lineDataset = new XYSeriesCollection ();

        a = a - 1;
        b = b + 1;

        XYSeries function = new XYSeries("function");

        double len = b-a;
        for (double i = a; i <= b; i+=len/120) {
            function.add(i,i - lambda * fservice.f(i));
        }

        XYSeries zero = new XYSeries("zero");
        zero.add(a,0);
        zero.add(b,0);


        lineDataset.addSeries(function);
        lineDataset.addSeries(zero);

        rawDraw(lineDataset,fservice);

    }

    public void draw(double a, double b, FunctionService fservice){
        XYSeriesCollection lineDataset = new XYSeriesCollection ();

        a = a - 1;
        b = b + 1;


        XYSeries function = new XYSeries("function");
        double len = b-a;
        for (double i = a; i <= b; i+=len/120) {
            function.add(i,fservice.f(i));
        }

        XYSeries zero = new XYSeries("zero");
        zero.add(a,0);
        zero.add(b,0);


        lineDataset.addSeries(function);
        lineDataset.addSeries(zero);

        rawDraw(lineDataset,fservice);

    }
}