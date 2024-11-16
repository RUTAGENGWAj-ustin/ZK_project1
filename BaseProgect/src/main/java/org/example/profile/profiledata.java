package org.example.profile;

public class profiledata {
    private String account;
    private String fullName;
    private String email;
    private String birthday;
    private String country;
    private String bio;

    public profiledata(String account, String fullName, String email, String birthday, String country, String bio) {
        this.account = account;
        this.fullName = fullName;
        this.email = email;
        this.birthday = birthday;
        this.country = country;
        this.bio = bio;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
