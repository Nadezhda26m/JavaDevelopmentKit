package ru.jdk.storage;

public interface Repository {
    void append(String text);
    String getLog();
}
