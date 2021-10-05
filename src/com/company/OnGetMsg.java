package com.company;

import javax.xml.crypto.Data;

public class OnGetMsg {
    public void sendMsg(DataPacket data){};
    public int verify(String token){return 0;}
    public boolean verifyUser(String user){return false;}
    public boolean registerUser(String userName){return false;}
}
