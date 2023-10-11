package ru.jdk.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame {
    private static final int WINDOW_HEIGHT = 450;
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_POSX = 100;
    private static final int WINDOW_POSY = 200;

    private JPanel panelLogin, panelSendMsg;
    private final JTextField tfIpAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("Ivan_Ivanovich");
    private final JPasswordField tfPassword = new JPasswordField("12345");
    private JButton btnLogin, btnSend;
    private JTextArea log;
    private final JTextField tfMessage = new JTextField();
    private boolean isClientConnected = false;

    Client client;
    private ServerWindow server;

    public ClientGUI(ServerWindow serverWindow) {
        this.server = serverWindow;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat client");
        setVisible(true);
        add(createPanelLogin(), BorderLayout.NORTH);
        add(createFieldLog(), BorderLayout.CENTER);
        add(createPanelSendMsg(), BorderLayout.SOUTH);
    }

    private Component createPanelLogin() {
        panelLogin = new JPanel(new GridLayout(2, 3));
        panelLogin.setVisible(true);
        panelLogin.add(tfIpAddress);
        panelLogin.add(tfPort);
        panelLogin.add(new JLabel());
        panelLogin.add(tfLogin);
        panelLogin.add(tfPassword);
        panelLogin.add(createBtnLogin());
        return panelLogin;
    }

    private Component createBtnLogin() {
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkConnection()) {
                    isClientConnected = true;
                    panelLogin.setVisible(false);
                    panelSendMsg.setVisible(true);
                    log.append("---Соединение установлено---\n");
                    log.append(server.logFile.getLogInfo());
                }
            }
        });
        return btnLogin;
    }

    private Component createFieldLog() {
        log = new JTextArea();
        log.setVisible(true);
        log.setEditable(false);
        return new JScrollPane(log);
    }


    private Component createPanelSendMsg() {
        panelSendMsg = new JPanel(new BorderLayout());
        panelSendMsg.setVisible(false);
        panelSendMsg.add(tfMessage, BorderLayout.CENTER);
        panelSendMsg.add(createBtnSend(), BorderLayout.EAST);
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n' && !tfMessage.getText().isEmpty()) {
                    sendMessage();
                }
            }
        });
        return panelSendMsg;
    }

    private Component createBtnSend() {
        btnSend = new JButton("send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        return btnSend;
    }

    private void sendMessage() {
        if (!tfMessage.getText().isEmpty() && checkConnection()) {
            String message = tfLogin.getText() + ": " + tfMessage.getText() + "\n";
            log.append(message);
            server.logArea.append(message);
            tfMessage.setText(null);
            server.logFile.addNewMessage(message);
        }
    }

    private boolean checkConnection() {
        if (!server.serverStart) {
            log.append("!!!Соединение с сервером отсутствует\n");
            return false;
        }
        // isClientConnected ???
        return true;
    }
}
