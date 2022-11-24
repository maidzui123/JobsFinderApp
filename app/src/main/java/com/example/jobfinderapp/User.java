package com.example.jobfinderapp;

public class User {
    public String email,userName, profilePicture;

    public User(){

    }
    public User(String email,String userName, String profilePicture){
        this.email=email;
        this.userName=userName;
        this.profilePicture=profilePicture;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
