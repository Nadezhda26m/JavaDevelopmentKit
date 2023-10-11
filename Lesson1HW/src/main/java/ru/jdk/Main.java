package ru.jdk;

import ru.jdk.server.ClientGUI;
import ru.jdk.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow window = new ServerWindow();
        new ClientGUI(window);
    }
}
