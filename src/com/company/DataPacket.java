package com.company;

import java.io.Serializable;

public class DataPacket implements Serializable {
    private String token="iishanto";
    private String msg="Hello world";
    private String image=null;
    private long timestamp=1111111111;

    public static int USER_VERIFICATION_REQUEST=1;
    public static int INCOMING_MSG=2;
    public static int MSG_DELIVERY_REPORT=3;
    public static int MSG_SEEN_REPORT=4;
    public static int OUTGOING_MSG=7;
public static int USER_REG_REQUEST=8;
    public static int AUTH_ERR=5;
    public static int ERROR=6;

    public boolean isServerMsg() {
        return serverMsg;
    }

    public void setServerMsg(boolean serverMsg) {
        this.serverMsg = serverMsg;
    }

    boolean serverMsg=false;

    private int error=-1;

    public void setErrorStat(int error) {
        this.error = error;
    }
    public int getErrorStat() {
        return error;
    }

    public String errorMsg;

    public String getError() {
        return errorMsg;
    }

    public void setError(String error) {
        this.errorMsg = error;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String receiver=null;

    public int getObjectType() {
        return ObjectType;
    }

    public void setObjectType(int objectType) {
        ObjectType = objectType;
    }

    int ObjectType=-1;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
