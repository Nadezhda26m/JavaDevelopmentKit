package ru.jdk.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage implements Repository {
    private String STORAGE_PATH = "Lesson2HW/src/main/java/ru/jdk/log/";
    private File logFile;

    public FileStorage() {
        new File(STORAGE_PATH).mkdirs();
        STORAGE_PATH += "log.txt";
        this.logFile = new File(STORAGE_PATH);
        try {
            if (!logFile.exists()) logFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void append(String text) {
        try (FileWriter writer = new FileWriter(STORAGE_PATH, true)) {
            writer.write(text + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLog() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufReader = new BufferedReader(new FileReader(logFile));) {
            String line = bufReader.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = bufReader.readLine();
            }
            if (!sb.isEmpty()) {
                sb = sb.deleteCharAt(sb.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
