package ru.nsu.fit.oop.lab1;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StatCollector {
    private static int totalWordCount;
    private InputStream inputStream;

    public StatCollector(InputStream userStream){
        inputStream = userStream;
        totalWordCount = 0;
    }

    public Map<String, Integer> buildHistogram(boolean caseSensetive) throws IOException {
        Map<String, Integer> words = new HashMap<>();
        totalWordCount = 0;
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = inputStream.read()) != -1 ){
            char cc = (char)c;
            if (!caseSensetive){
                cc = Character.toLowerCase(cc);
            }
            if (Character.isLetterOrDigit(cc)){
                sb.append(cc);
            } else if (sb.length() > 0) {
                words.compute(sb.toString(), (k,v) -> v == null ? 1 : v + 1);
                sb.setLength(0);
                totalWordCount++;
            }
        }

        if (sb.length() > 0) {
            words.compute(sb.toString(), (k,v) -> v == null ? 1 : v + 1);
            totalWordCount++;
        }
        return words;
    }

    public int countWords() {
        return totalWordCount;
    }
}
