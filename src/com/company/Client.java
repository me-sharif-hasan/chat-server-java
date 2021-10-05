package com.company;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Client extends Thread {

    Socket s;
    public Client(Socket s) {
        System.out.println("New client connection created.");
        this.s = s;
    }
    @Override
    public void run(){

        new MessageEventHandler(s).addEvent(new OnGetMsg() {
            @Override
            public void sendMsg(DataPacket data) {
                ServerUserManagerSingleton.getInstance().sendMsg(data);
            }

            @Override
            public boolean verifyUser(String user) {
                return ServerUserManagerSingleton.getInstance().verifyUser(user);
            }

            @Override
            public boolean registerUser(String userName) {
                return ServerUserManagerSingleton.getInstance().addUser(userName);
            }
        });
}
