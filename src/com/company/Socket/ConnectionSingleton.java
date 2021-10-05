package com.company.Socket;

import com.company.DataPacket;

import javax.xml.crypto.Data;
import java.io.*;
import java.lang.instrument.Instrumentation;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConnectionSingleton {
    private String server="localhost";
    private int port=8080;
    static ConnectionSingleton instance;
    private int maxConnectionAttempt=5;

    private Socket connectionSocket=null;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    private ConnectionSingleton(){

    }

    private int connectionAttempt=0;
    public boolean connect(){
        try {
            connectionSocket=new Socket(server,port);
            oos=new ObjectOutputStream(connectionSocket.getOutputStream());
            ois=new ObjectInputStream(connectionSocket.getInputStream());
            connectionAttempt=0;
            return true;
        } catch (IOException e) {
            if(connectionAttempt<maxConnectionAttempt){
                connectionAttempt++;
                return connect();
            }else{
                System.out.println("ii: connection failed: "+e.getLocalizedMessage());
                return false;
            }
        }

    }
    public boolean verify(String user) throws Exception{
        String verificationString="msg_mania_verification_user:"+user+"\n";
        if(connectionSocket==null){
            throw new ConnectException("You must connect to the server first. Please use connect() method.");
        }
        System.out.println("Trying to verify");
        DataPacket dp=new DataPacket();
        dp.setObjectType(DataPacket.USER_VERIFICATION_REQUEST);
        dp.setMsg("USER VERIFICATION");
        dp.setToken(verificationString);

        oos.writeObject(dp);
        //oos.reset();
        DataPacket d=(DataPacket) ois.readObject();
        System.out.println(d.getMsg());
        return true;
    }




    public static ConnectionSingleton getSingleton(){
        if(instance==null) instance=new ConnectionSingleton();
        return instance;
    }

    public void send(String msg) throws Exception{
        DataPacket d=new DataPacket();
        d.setObjectType(DataPacket.OUTGOING_MSG);
        d.setMsg(msg);
        oos.writeObject(d);
        System.out.println("DATA SENT");

        DataPacket kd=(DataPacket) ois.readObject();
        System.out.println(kd.getError());
    }

    public void registerUser(String user) throws Exception {
        DataPacket d=new DataPacket();
        d.setObjectType(DataPacket.USER_REG_REQUEST);
        d.setToken("iishanto");
        oos.writeObject(d);
    }
}




 class ObjectSizeFetcher {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}