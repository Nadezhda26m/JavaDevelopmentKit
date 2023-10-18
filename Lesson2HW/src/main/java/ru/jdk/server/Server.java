package ru.jdk.server;

import ru.jdk.client.Client;
import ru.jdk.storage.Repository;

import java.util.ArrayList;

public class Server {


    ArrayList<Client> clientList;
    boolean work;
    ServerView view;
    Repository repository;

    public Server(ServerView view, Repository repository) {
        this.clientList = new ArrayList<>();
        this.view = view;
        this.repository = repository;
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        clientList.add(client);
        return true;
    }

    public boolean isServerWork() {
        return work;
    }

    public String getHistory() {
        return repository.getLog();
    }

    public void disconnectUser(Client client) {
        clientList.remove(client);
        if (client != null) {
            client.disconnectFromServer();
        }
    }

    public void disconnectAllUsers() {
        while (clientList.size() != 0) {
            Client client = clientList.get(0);
            client.disconnectFromServer();
        }
    }

    public void sendMessage(String text) {
        if (!work) {
            return;
        }
        appendLog(text);
        answerAll(text);
        saveInRepository(text);
    }

    private void saveInRepository(String text) {
        repository.append(text);
    }

    private void answerAll(String text) {
        for (Client client : clientList) {
            client.printText(text);
        }
    }

    public void appendLog(String text) {
        view.printText(text);
    }

    public void startServer() {
        if (work) {
            view.printText("Сервер уже был запущен");
        } else {
            work = true;
            view.printText("Сервер запущен!");
        }
    }

    public void stopServer() {
        if (!work) {
            view.printText("Сервер уже был остановлен");
        } else {
            work = false;
            disconnectAllUsers();
            view.printText("Сервер остановлен!");
        }
    }

    public boolean isLoginAlreadyExists(String login) {
        for (Client client : clientList) {
            if (client.getName().equals(login)) return true;
        }
        return false;
    }

}
