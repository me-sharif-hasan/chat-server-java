package com.iishanto.MsgManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    Queue <Message> msgQ=new LinkedList<>();
    public void push(Message msg){
        msgQ.add(msg);
    }
    public void push(String msgJSON) throws ParseException {
        JSONObject msg= (JSONObject) new JSONParser().parse(msgJSON);
        Message newMsg=new Message(msg);
        push(newMsg);
    }

    public Message getOne(){
        return msgQ.peek();
    }
    public void deleteTop(){
        if(!msgQ.isEmpty()){
            msgQ.remove();
        }
    }
}
