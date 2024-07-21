package org.example.consoleInterface;

import org.example.models.Type;
import org.example.readers.Parser;
import org.example.statistics.Statistics;
import org.example.writers.Writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleInterface {
    private final Parser parser;
    private final Writer writer;
    private final Statistics statistics;
    private final ArrayList<String> files;

    private final HashMap<Type, ArrayList<String>> map;

    public ConsoleInterface(ArrayList<String> inputFiles, String outputPath, String prefix, boolean append, boolean fullFormat, boolean shortFormat) throws IOException {
        if (inputFiles == null) {
            throw new IOException("Uncorrected params");
        }
        writer = new Writer();
        parser = new Parser();
        statistics = new Statistics();
        files = new ArrayList<>();
        map = new HashMap<>();

        files.addAll(inputFiles);

        writer.setPath(outputPath);
        writer.setPrefix(prefix);
        writer.setAppend(append);

        statistics.setShortFormat(shortFormat);
        statistics.setFullFormat(fullFormat);
    }

    public void run() throws IOException {
        readFile();
        statistics.printInfo(map);
        writer.write(map);
    }

    private void readFile() throws IOException {
        for (String file : files) {
            try (Scanner scanner = new Scanner(new File(file))) {
                while (scanner.hasNextLine()) {
                    String str = scanner.nextLine();
                    Type type = parser.parse(str);
                    if (!map.containsKey(type)) {
                        map.put(type, new ArrayList<>());
                    }
                    map.get(type).add(str);
                }
            } catch (IOException e) {
                throw new IOException("No such file or directory");
            }
        }
    }
}
