package ru.jdk.client;

public interface ClientView {
    void showMessage(String text);
    void disconnectFromServer();
    void clearLog();
}
