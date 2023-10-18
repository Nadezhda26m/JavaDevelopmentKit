package ru.jdk;

import ru.jdk.client.ClientGUI;
import ru.jdk.storage.FileStorage;
import ru.jdk.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow(new FileStorage());
        new ClientGUI(serverWindow.getServer());
        new ClientGUI(serverWindow.getServer());
    }
}
