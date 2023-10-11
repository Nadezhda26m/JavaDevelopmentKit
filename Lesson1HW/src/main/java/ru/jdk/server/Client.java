package ru.jdk.server;

public class Client {
    private String login, password;

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    static boolean checkLogin() {
        return true;
    }

    static boolean checkPassword() {
        return true;
    }
}
