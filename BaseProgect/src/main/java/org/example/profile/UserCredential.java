package org.example.profile;

public class UserCredential {
    private String account;
    private boolean anonymous;

    // Constructors
    public UserCredential() {
        this.account = "";
        this.anonymous = true; // Default to anonymous
    }

    public UserCredential(String account) {
        this.account = account;
        this.anonymous = (account == null || account.isEmpty());
    }

    // Getters and Setters
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        this.anonymous = (account == null || account.isEmpty());
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
