package com.iishanto.PressenceModule;

import com.iishanto.UserManager.UserActionInterface;
import com.iishanto.UserManager.UserManagerSingleton;

public class PresenceLog {
    public void isValid(String username, UserActionInterface uai){
        UserManagerSingleton.getInstance().verifyUserThenDoAction(username,uai);
    }
}
