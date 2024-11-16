package org.example.profile;

import java.util.Date;

public class User {
    private String username;
    private String fullName;
    private String email;
    private Date birthday;
    private String bio;
    private String country;
    private String account;

    private String password;
    public User(){

    }

   public User(String username,String fullName,String email,Date birthday,String bio,String country,String account,String password){
       this.username = username;
       this.fullName = fullName;
       this.email = email;
       this.birthday = birthday;
       this.bio = bio;
       this.country = country;
       this.account = account;
       this.password = password;
   }
    // Getters and Setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
