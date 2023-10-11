package ru.jdk.server;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServerLog {
    private final String PATH_DB = "Lesson1HW/src/main/java/ru/jdk/log.txt";
    private File logFile;

    public ServerLog() {
        this.logFile = new File(PATH_DB);
        try {
            if (!logFile.exists()) logFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void addNewMessage(String text) {
        try(FileWriter writer = new FileWriter(PATH_DB, true)) {
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
