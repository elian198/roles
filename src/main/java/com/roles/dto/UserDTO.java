package com.roles.dto;

import com.roles.entities.User;

public class UserDTO {

    private String userName;

    private String email;

    private String password;

    public UserDTO() { }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User transformToUser(){
    User user = new User();
    user.setUserName(userName);
    user.setEmail(email);
    user.setPassowrd(password);

    return user;
    }
}
