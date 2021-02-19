package ru.nsu.fit.oop.lab1;
import javax.imageio.IIOException;
import java.io.*;
import java.util.List;
import java.util.Map;

public class CSVbuilder {
    private Writer writer;

    public CSVbuilder(OutputStream userStream){
        writer = new OutputStreamWriter(userStream);
    }

    public void createCSV(List<Map.Entry<String, Integer>> list, int totalWordCount) throws IOException {
        for (var e : list){
            String percent = Float.toString(100.0f * e.getValue() / totalWordCount);
            writer.append(e.getKey())
                    .append(',')
                    .append(e.getValue().toString())
                    .append(',')
                    .append(percent)
                    .append('\n');
            writer.flush();
        }
    }
}
