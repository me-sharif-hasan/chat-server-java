package com.iishanto.UserManager;

import com.iishanto.json.JsonStreamReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

class DynamicUserLoadingService extends Thread{
    JsonStreamReader userDB;
    public DynamicUserLoadingService(InputStream userDB){
        System.out.println("User loading service created ");
        this.userDB=new JsonStreamReader(userDB);
    }

    public void run() {
        while (true){
            try {
                String json=userDB.nextObject();
                if(json.equals("")) continue;

                User newUser=new User(json);
                UserManagerSingleton.getInstance().addUser(newUser);
                System.out.println("A new user added. now total user= "+UserManagerSingleton.getInstance().getTotalUser());
                System.out.println(json);
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}
