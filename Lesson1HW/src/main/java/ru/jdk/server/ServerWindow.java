package ru.jdk.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ServerWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 450;
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_POSX = 600;
    private static final int WINDOW_POSY = 200;

    JButton btnStart, btnStop;
    boolean serverStart;
    TextArea logArea;
    ServerLog logFile;

    // List<ClientGUI> clients

    public ServerWindow() {
        this.logFile = new ServerLog();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat server");
        setVisible(true);
        serverStart = false;

        add(createTextArea(), BorderLayout.CENTER);
        add(createMainPanel(), BorderLayout.SOUTH);
    }

    private Component createTextArea() {
        logArea = new TextArea();
        logArea.setVisible(true);
        logArea.setEditable(false);
        logArea.setBackground(Color.WHITE);
        logArea.append("Server doesn't work\n");
        logArea.append(logFile.getLogInfo());
        return logArea;
    }

    private Component createMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(createButtonStart());
        mainPanel.add(createButtonStop());
        return mainPanel;
    }

    private Component createButtonStart() {
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!serverStart) {
                    serverStart = true;
                    logArea.append("Server started\n");
                } else {
                    logArea.append("Server is already running\n");
                }
            }
        });
        return btnStart;
    }

    private Component createButtonStop() {
        btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverStart) {
                    serverStart = false;
                    logArea.append("Server stopped\n");
                } else {
                    logArea.append("Server was stopped\n");
                }
            }
        });
        return btnStop;
    }
}
