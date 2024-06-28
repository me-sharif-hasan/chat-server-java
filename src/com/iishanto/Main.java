package com.iishanto;

import com.iishanto.PressenceModule.PresenceLog;
import com.iishanto.UserManager.User;
import com.iishanto.UserManager.UserActionInterface;
import com.iishanto.UserManager.UserManagerSingleton;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception{
        UserManagerSingleton.getInstance().loadUsers();
        TimeUnit.MILLISECONDS.sleep(1000);



        int x=5;

    }
}
