package ru.nsu.fit.oop.lab1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

public class Sorter {

    public enum sortRule{
        ascending,
        descending
    }

    public List<Map.Entry<String, Integer>> mapSorter(Map<String, Integer> map, sortRule rule){
        var list =  new ArrayList<>(map.entrySet());
        if (rule == sortRule.descending){
            list.sort((a, b) -> {
                int rs = b.getValue().compareTo(a.getValue());
                if (rs == 0) {
                    rs = a.getKey().compareTo(b.getKey());
                }
                return rs;
            });
        } else if (rule == sortRule.ascending){
            list.sort(Comparator.comparingInt((ToIntFunction<Map.Entry<String, Integer>>) Map.Entry::getValue).thenComparing(Map.Entry::getKey));
        }
        return list;
    }
}
