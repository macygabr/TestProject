package org.example.writers;

import org.example.models.Type;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class Writer {
    private String path;
    private String prefix;
    private Boolean append;
    Properties properties;

    public Writer() throws IOException {
        path = ".";
        prefix = "";

        properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (is == null) {
                throw new IOException("application.properties file not found");
            }
            properties.load(is);
        }
    }

    public void write(HashMap<Type, ArrayList<String>> map) throws IOException {
        for (HashMap.Entry<Type, ArrayList<String>> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) throw new IOException("Unknown type");
            String fileName = getFileName(entry.getKey());
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(path, prefix + fileName).toFile(), append))) {
                for (String str : entry.getValue()) {
                    writer.write(str);
                    writer.newLine();
                }
            }
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

    private String getFileName(Type type) throws IOException {
        switch (type) {
            case INTEGER:
                return properties.getProperty("app.int");
            case FLOAT:
                return properties.getProperty("app.float");
            case STRING:
                return properties.getProperty("app.string");
            default:
                throw new IOException("Unknown type");
        }
    }
}
