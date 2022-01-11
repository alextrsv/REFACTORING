package com.kekens.refactoring_lab_2_server.entities;

public class UserDTO {
    String userName;
    String password;

    public UserDTO(){
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
