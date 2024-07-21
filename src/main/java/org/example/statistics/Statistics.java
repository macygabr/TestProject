package org.example.statistics;

import lombok.Setter;
import org.example.models.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Statistics {
    @Setter
    private boolean shortFormat;
    @Setter
    private boolean fullFormat;
    private HashMap<Type, ArrayList<String>> map;

    public void printInfo(HashMap<Type, ArrayList<String>> map) throws IOException {
        if (map == null || map.isEmpty()) throw new IOException("Error with read files");
        this.map = map;
        if (fullFormat) {
            getFullInfo();
        } else if (shortFormat) {
            getShortInfo();
        }
    }

    private void getShortInfo() {
        map.forEach((key, val) -> {
            if (key == null || val == null) throw new RuntimeException("Error with read files");
            System.out.println(key + ": " + val.size());
        });
    }

    private void getFullInfo() {
        getShortInfo();
        map.forEach((key, val) -> {
            if (key == null || val == null) throw new RuntimeException("Error with read files");
            if (key != Type.STRING) {
                ArrayList<Double> numericValues = (ArrayList<Double>) val.stream().map(Double::parseDouble).collect(Collectors.toList());
                double max = numericValues.stream().mapToDouble(Double::doubleValue).max().orElse(Double.NaN);
                double min = numericValues.stream().mapToDouble(Double::doubleValue).min().orElse(Double.NaN);
                double sum = numericValues.stream().mapToDouble(Double::doubleValue).sum();
                double average = numericValues.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);

                System.out.println(key + " max: " + max);
                System.out.println(key + " min: " + min);
                System.out.println(key + " sum: " + sum);
                System.out.println(key + " average: " + average);
            }

            if (key == Type.STRING) {
                int shortest = val.stream().mapToInt(String::length).min().orElse(0);
                int longest = val.stream().mapToInt(String::length).max().orElse(0);

                System.out.println(key + " shortest: " + shortest);
                System.out.println(key + " longest: " + longest);
            }
        });
    }
}
