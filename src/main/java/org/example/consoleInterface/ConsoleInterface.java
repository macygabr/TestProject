package org.example.consoleInterface;

import lombok.Builder;
import org.example.models.Type;
import org.example.readers.Parser;
import org.example.statistics.Statistics;
import org.example.writers.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInterface {
    private final Parser parser = new Parser();
    private final Writer writer = new Writer();
    private final Statistics statistics = new Statistics();
    private final ArrayList<String> files = new ArrayList<>();

    public ConsoleInterface(ArrayList<String> inputFiles, String outputPath, String prefix, boolean append, boolean fullFormat, boolean shortFormat) {
        if (inputFiles == null) {
            System.out.println("Uncorrected args");
            return;
        }

        files.addAll(inputFiles);

        writer.setPath(outputPath);
        writer.setPrefix(prefix);
        writer.setAppend(append);

        statistics.setShortFormat(shortFormat);
        statistics.setFullFormat(fullFormat);
    }

    public void run() {
        for (String file : files) {
            readFile(file);
        }
    }

    private void readFile(String file) {
        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                Type type = parser.parse(str);
                statistics.getInfo(type, str);
                writer.write(type, str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
