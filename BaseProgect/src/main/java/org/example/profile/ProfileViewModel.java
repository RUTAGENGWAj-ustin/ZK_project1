package org.example.profile;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;

import java.io.Serializable;
import java.util.List;

public class ProfileViewModel implements Serializable {
    //services
    private AuthenticationService authenticationService = new AuthenticationServiceChapter3Impl();
    private UserInfoService userInfoService = new UserInfoServiceChapter3Impl();
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }
    public List<String> getCountrylist() {
        return CommonInfoService.getCountryList();
    }

    public ProfileViewModel(){
        UserCredential userCredential = authenticationService.getUserCredential();
        currentUser = userInfoService.findUser(userCredential.getAccount());
        if (currentUser == null){
            return;
        }

    }
    @Command
    @NotifyChange("currentUser")
    public void save(){
        userInfoService.updateUserInfo(currentUser);
        Clients.showNotification("Your profile is updated");
    }
    @Command
    @NotifyChange("currentUser")
    public void reload(){
   UserCredential cre = authenticationService.getUserCredential();
   currentUser = userInfoService.findUser(cre.getAccount());
    }
}
