package ru.jdk.client;

import ru.jdk.server.Server;

public class Client {
    private String name;
    private ClientView clientView;
    private Server server;
    private boolean connected;

    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    public boolean connectToServer(String name) {
        if (server.isServerWork()) {
            if (checkLogin(name) && server.connectUser(this)) {
                clientView.clearLog();
                printText("Вы успешно подключились!");
                connected = true;
                String log = server.getHistory();
                if (log != null) {
                    printText(log);
                }
                return true;
            } else return false;
        } else {
            printText("Подключение не удалось");
            return false;
        }
    }

    private boolean checkLogin(String login) {
        if (login == null || login.isEmpty()) {
            clientView.showMessage("Логин не заполнен");
            return false;
        } else if (login.matches("[A-Za-z]+_?[A-Za-z0-9]+")) {
            this.name = login;
            return true;
        } else {
            clientView.showMessage("Неверный логин");
            return false;
        }
    }

    // мы посылаем
    public void sendMessage(String message) {
        if (connected) {
            if (!message.isEmpty()) {
                server.sendMessage(name + ": " + message);
            }
        } else {
            printText("Нет подключения к серверу");
        }
    }

    // нам присылают
    public void serverAnswer(String answer) {
        printText(answer);
    }

    public void disconnectFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            printText("Вы были отключены от сервера!");
        }
    }

    public String getName() {
        return name;
    }

    public void printText(String text) {
        clientView.showMessage(text);
    }
}
