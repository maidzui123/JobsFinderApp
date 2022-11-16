package com.example.jobfinderapp;

public class User {
    public String email,userName, passWord;

    public User(){

    }
    public User(String email,String userName, String passWord){
        this.email=email;
        this.userName=userName;
        this.passWord=passWord;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
