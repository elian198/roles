package com.roles.dto;

import com.roles.entities.User;

public class UserDTO {

    private Long id;

    private String userName;

    private String email;

    private String password;

    private Boolean soft_delete;

    public UserDTO() { }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getSoft_delete() {
        return soft_delete;
    }

    public void setSoft_delete(Boolean soft_delete) {
        this.soft_delete = soft_delete;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public User transformToUser(){
    User user = new User();
    user.setId(id);
    user.setUserName(userName);
    user.setEmail(email);
    user.setPassowrd(password);
    user.setSorfDelete(soft_delete);

    return user;
    }
}
