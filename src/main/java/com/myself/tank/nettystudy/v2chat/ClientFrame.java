package com.myself.tank.nettystudy.v2chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends Frame {

    TextArea ta = new TextArea();
    TextField tf = new TextField();

    Client c = null;

    public static final ClientFrame INSTANCE = new ClientFrame();

    private ClientFrame(){
        this.setSize(600, 400);
        this.setLocation(100, 20);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf, BorderLayout.SOUTH);
        tf.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                c.send(tf.getText());
                ta.setText(ta.getText() + tf.getText());
                tf.setText("");

            }
        });
        this.setVisible(true);
    }

    public void write(String msg){
        ta.setText(msg);
    }

    private void connectToServer(){
        c = new Client();
        c.connect();
    }

    public static void main(String[] args) {
        INSTANCE.connectToServer();
    }
}
