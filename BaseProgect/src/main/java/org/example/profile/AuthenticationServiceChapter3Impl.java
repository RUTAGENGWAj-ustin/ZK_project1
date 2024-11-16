package org.example.profile;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthenticationServiceChapter3Impl implements AuthenticationService, Serializable {

List<User> users = new ArrayList<>();
    public AuthenticationServiceChapter3Impl(){
        users.add(new User("john" ,"john","john@gmail.com",new Date(),"hey","rwanda","john","12345"));
    }
    @Override
    public User getLoggedInUser() {
        // Mock implementation
        User user = new User();
        user.setUsername("john_doe");
        user.setFullName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setBirthday(new java.util.Date());
        user.setBio("Software developer from the USA.");
        user.setCountry("United States");
        user.setAccount(user.getUsername());
        return user;
    }

    @Override
    public UserCredential getUserCredential() {
//        UserCredential credential = new UserCredential("john_doe");
//        credential.setAccount("john_doe");
////        credential.setPassword("password123");  // Ideally, store passwords securely.
////        credential.setRoles(List.of("USER", "ADMIN"));  // Example roles.
//        return credential;
        Session session = Sessions.getCurrent();
        UserCredential cre = (UserCredential)session.getAttribute("userCredential");
        if(cre==null){
            cre = new UserCredential("");//new a anonymous user and set to session
            session.setAttribute("userCredential",cre);
        }
        return cre;
    }
    @Override
    public boolean login(String account, String password) {
        // Simple authentication check (replace with real validation)
        UserCredential userCredential;
        if ("admin".equals(account) && "password".equals(password)) {
            userCredential = new UserCredential( "Admin User");
            userCredential.setAnonymous(false);
            Sessions.getCurrent().setAttribute("userCredential", userCredential); // Store in session
            return true;
        } else {
            userCredential = new UserCredential( null);
            userCredential.setAnonymous(true);
            return false;
        }
    }
}
