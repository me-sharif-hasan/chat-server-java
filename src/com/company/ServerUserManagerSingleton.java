package com.company;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerUserManagerSingleton {
    private static ServerUserManagerSingleton instance;
    private ServerUserManagerSingleton(){}

    Map clients=new HashMap<String, Socket>();

    public boolean verifyUser(String user){
        return clients.containsKey(user);
    }

    public boolean addUser(String name){
        clients.put(name,null);
        return true;
    }

    public boolean sendMsg(DataPacket data){
        if(verifyUser(data.getReceiver())&&verifyUser(data.getToken())){



        }else{
            return false;
        }
        return true;
    }

    public static ServerUserManagerSingleton getInstance(){
        if(instance==null) instance=new ServerUserManagerSingleton();
        return instance;
    }
}
