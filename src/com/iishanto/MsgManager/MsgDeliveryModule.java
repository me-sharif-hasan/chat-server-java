package com.iishanto.MsgManager;

import com.iishanto.UserManager.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MsgDeliveryModule extends Thread{
    User user;
    public MsgDeliveryModule(User user){
        this.user=user;
    }

    @Override
    public void run() {
        realTimeSendMsgFromPendingMsg();
    }
    void realTimeSendMsgFromPendingMsg(){
        Socket s=user.getSocket();
        try {
            OutputStream os=s.getOutputStream();
            while (true){
                String msg=user.getMsgQueue().getOne().getJSONMsg();
                os.write(msg.getBytes());
                // todo get the delivery report then remove msg or try again


            }
        } catch (IOException e) {
            System.out.println("failed to start msg delivery module. please try again.");
            user.setActive(null);
        }
    }
}
