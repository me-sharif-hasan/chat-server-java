package com.company;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MessageEventHandler extends Thread{
    Socket socket;
    OnGetMsg events;
    static int VERIFY_REQ=0;
    static int SEND_MSG_REQ=1;
    public MessageEventHandler(Socket s){
        socket=s;
    }
    public void addEvent(OnGetMsg onGetMsg){
        events=onGetMsg;
        start();
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            ObjectInputStream ois=new ObjectInputStream(is);
            ObjectOutputStream oos=new ObjectOutputStream(os);

            DataPacket dp;
            while (true){
                System.out.println("reading data object");
                //ois.reset();
                dp=(DataPacket)ois.readObject();
                //System.out.println(dp.getObjectType()+":::");
                DataPacket d=new DataPacket();
                d.setServerMsg(true);
                if(dp.getObjectType()==DataPacket.USER_VERIFICATION_REQUEST){
                    boolean stat=events.verifyUser(dp.getToken());
                    System.out.println("SERVER: USER VERIFICATION REQUEST GOT "+stat);
                    if(stat){
                        d.setMsg("101: USER VERIFICATION SUCCESS");
                    }else{
                        d.setErrorStat(DataPacket.ERROR);
                        d.setMsg("201: USER VERIFICATION FAILED");
                    }
                }else if(dp.getObjectType()==DataPacket.OUTGOING_MSG){
                    if(dp.getReceiver()==null){
                        d.setErrorStat(DataPacket.ERROR);
                        d.setError("202: PLEASE PROVIDE A TARGET USER");
                    }else {
                        d.setErrorStat(DataPacket.ERROR);
                        events.sendMsg(dp);
                        d.setMsg("102: MSG SENT TO RECEIVER");
                    }
                }else if(dp.getObjectType()==DataPacket.USER_REG_REQUEST){
                    boolean stat=events.registerUser(dp.getToken());
                    if(stat){
                        d.setMsg("103: USER REGISTRATION SUCCESS");
                    }else{
                        d.setErrorStat(DataPacket.ERROR);
                        d.setMsg("203: USER REGISTRATION FAILED");
                    }
                }else{
                    d.setErrorStat(DataPacket.ERROR);
                    d.setMsg("203: OBJECT NOT UNDERSTOOD");
                }
               // oos.reset();
                oos.writeObject(d);
                System.out.println(d.getMsg());
               // oos.reset();
            }


        }catch (Exception e){

        }
    }
}
