package org.example.profile;

public class UserInfoServiceChapter3Impl implements UserInfoService{

    @Override
    public void updateUserInfo(User user) {
        // Mock implementation of updating user info
        System.out.println("User information updated: " + user.getFullName());
        // In a real-world scenario, this might save user data to a database
    }

    @Override
    public User findUser(String account) {
        User user = new User();
        user.setUsername("john_doe");
        user.setFullName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setBirthday(new java.util.Date());
        user.setBio("Software developer from the USA.");
        user.setCountry("United States");
        return user;
    }
}
