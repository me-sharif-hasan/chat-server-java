package com.iishanto.UserManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UserManagerSingleton {
    private HashMap <String, User> userList;
    final private String userDatabaseStoreLocation="/home/iishanto/IdeaProjects/ChatServer/src/com/iishanto/userDBStore.json";
    final private String userDatabaseFromLocation="/home/iishanto/IdeaProjects/ChatServer/src/com/iishanto/userDBFrom.json";
    FileOutputStream userDB;
    FileInputStream userDBRead;
    public static UserManagerSingleton instance;

    private UserManagerSingleton(){
        userList=new HashMap<>();
        dbReload();
    };
    public void loadUsers(){
        new DynamicUserLoadingService(userDBRead).start();
    }
    public void addUser(String username) throws Exception {
        User user=new User(username, 0);
        userList.put(username,user);
        saveUser(user.getUsername());
    }
    public void addUser(User user) throws Exception{
        System.out.println("Adding user");
        userList.put(user.getUsername(),user);
        saveUser(user.getUsername());
    }

    public int getTotalUser(){
        return userList.size();
    }

    public boolean saveUser(String username) throws Exception {
        User user=userList.get(username);
        if(user==null) return false;
        user.saveToDB(userDB);
        return true;
    }

    public static UserManagerSingleton getInstance(){
        if(instance==null) {
            System.out.println("Creating new USerManager");
            instance=new UserManagerSingleton();
        }
        return instance;
    }



    /*
    @ Large class methods here please...
     */


    public void dbReload(){
        if(userDB!=null){
            try {
                userDB.close();
                if(userDBRead!=null){
                    userDBRead.close();
                }
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        try {
            userDB=new FileOutputStream(userDatabaseStoreLocation);
            userDBRead=new FileInputStream(userDatabaseFromLocation);
        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void verifyUserThenDoAction(String uname,UserActionInterface action){
        action.doAction(userList.get(uname));
    }

}
