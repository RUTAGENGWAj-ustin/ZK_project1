package org.example.profile;

public interface AuthenticationService {
    User getLoggedInUser();
    UserCredential getUserCredential();
    boolean login(String account, String password);
}
