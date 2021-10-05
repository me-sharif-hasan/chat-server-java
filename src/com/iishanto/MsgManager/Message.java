package com.iishanto.MsgManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Message {
    JSONObject msg;
    public Message(JSONObject json){
        this.msg=json;
    }
    public String getJSONMsg(){
        return msg.toJSONString();
    }
}
