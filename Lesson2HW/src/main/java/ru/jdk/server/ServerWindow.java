package ru.jdk.server;

import ru.jdk.storage.Repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame implements ServerView {
    public static final int WINDOW_HEIGHT = 450;
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_POSX = 600;
    private static final int WINDOW_POSY = 200;

    JButton btnStart, btnStop;
    JTextArea log;
    Server server;

    public ServerWindow(Repository repository) {
        server = new Server(this, repository);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        createPanel();
        setVisible(true);
    }

    public Server getServer() {
        return server;
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.startServer();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
            }
        });
        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    @Override
    public void printText(String text) {
        log.append(text + "\n");
    }
}
