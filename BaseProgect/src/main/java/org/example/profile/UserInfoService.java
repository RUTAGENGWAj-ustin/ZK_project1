package org.example.profile;

public interface UserInfoService {
    // Method to update user information
    void updateUserInfo(User user);
    User findUser(String account);
}

