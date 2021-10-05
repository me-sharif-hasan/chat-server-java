package com.company.Socket;

import java.util.concurrent.TimeUnit;

public class SocTest {
    public static void main(String[] args) throws Exception{
        ConnectionSingleton cs=ConnectionSingleton.getSingleton();
        System.out.println("Trying to connect");
        cs.connect();
        System.out.println("Connected!");
        //cs.registerUser("iishanto");
        cs.verify("iishanto");

        String msg="Sample msg";
        cs.send(msg);
    }
}
