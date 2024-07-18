package org.example;

import org.example.consoleInterface.ConsoleInterface;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.ArrayList;

public class Main {
    @Parameter(names = "-s")
    private static boolean shortFormat;

    @Parameter(names = "-f")
    private static boolean fullFormat;

    @Parameter(names = "-a")
    private static boolean append;

    @Parameter(names = "-o")
    private static String outputPath;

    @Parameter(names = "-p")
    private static String prefix;

    @Parameter()
    private static ArrayList<String> inputFiles;

    public static void main(String[] args) {

        Main main = new Main();
        JCommander jCommander = JCommander.newBuilder()
                .addObject(main)
                .build();
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            jCommander.usage();
            return;
        }

        new ConsoleInterface(inputFiles, outputPath, prefix, append, fullFormat, shortFormat).run();
    }
}