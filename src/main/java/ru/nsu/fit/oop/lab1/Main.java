package ru.nsu.fit.oop.lab1;

import java.io.*;
import org.apache.commons.cli.*;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Options options = new Options();
        options.addOption("f", "file", true,"Input file");
        options.addOption("o", "out", true, "Output file");
        CommandLineParser parser = new DefaultParser();
        InputStream in = System.in;
        OutputStream out = System.out;

        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("help")){
                return;
            }
            if (line.hasOption("file")){
                String file = line.getOptionValue("file");
                System.out.println("Reading from " + file );
                in = new FileInputStream("text.txt");
            }
            if (line.hasOption("out")){
                String file = line.getOptionValue("out");
                System.out.println("Writing to " + file);
                out = new FileOutputStream(file);
            }

        } catch (ParseException exp) {
            System.err.println("Parsing failed. Reason: " + exp.getMessage());
        }

        StatCollector statCollector = new StatCollector(in);
        Map<String, Integer> words = statCollector.buildHistogram(false);
        Sorter sorter = new Sorter();
        var list = sorter.mapSorter(words, Sorter.sortRule.descending);
        int totalWordCount = statCollector.countWords();
        CSVbuilder builder = new CSVbuilder(out);
        builder.createCSV(list, totalWordCount);
    }
}
