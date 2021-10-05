package com.iishanto.UserManager;

import com.iishanto.MsgManager.MessageQueue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class User {
    JSONObject userAttr;
    Socket userSoc=null;
    MessageQueue messageQueue=new MessageQueue();;
    public User(String username,int id){
        userAttr=new JSONObject();
        userAttr.put("username", username);
        userAttr.put("id",id);
    }
    public MessageQueue getMsgQueue(){
        return messageQueue;
    }
    public User(String json) throws ParseException {
        userAttr=(JSONObject) new JSONParser().parse(json);
    }

    public String getUsername(){
        return (String) userAttr.get("username");
    }
    public String getJSON(){
        return userAttr.toJSONString();
    }
    public void saveToDB(OutputStream userDatabaseOutputStream) throws Exception{
        userDatabaseOutputStream.write(getJSON().getBytes());
    }

    public void storeMSGJOSN(String msg){

    }

    public Socket getSocket(){
        return userSoc;
    }
    public boolean isActive(){
        return userSoc!=null;
    }
    public void setActive(Socket userSocket){
        userSoc=userSocket;
    }
}
