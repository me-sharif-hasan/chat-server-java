package com.company;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket(8080);
        Socket s;
        while (true){
            s=serverSocket.accept();
            new Client(s).start();
        }
    }
}
