package org.example.writers;

import lombok.Setter;
import org.example.models.Type;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Writer {
    private String path = ".";
    private String prefix = "";
    private Boolean append;

    private final String integerFileName = "integers.txt";
    private final String floatFileName = "floats.txt";
    private final String stringFileName = "strings.txt";

    public void write(Type type, String str) {
        String fileName = getFileName(type);
        if (fileName == null) {
            System.err.println("Unknown type: " + type);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(path, prefix + fileName).toFile(), true))) {
            writer.write(str);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void setPath(String path) {
        if (path != null) {
            this.path = path;
        }
    }

    public void setPrefix(String prefix) {
        if (prefix != null) {
            this.prefix = prefix;
        }
    }

    public void setAppend(Boolean append) {
        this.append = append;
    }

    private String getFileName(Type type) {
        switch (type) {
            case INTEGER:
                return integerFileName;
            case FLOAT:
                return floatFileName;
            case STRING:
                return stringFileName;
            default:
                return null;
        }
    }
}
