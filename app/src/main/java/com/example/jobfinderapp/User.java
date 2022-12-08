package com.example.jobfinderapp;

import android.net.Uri;

public class User {
    public String email, userName, fullName, birthDay, gender, phoneNumber, address, majors, profilePicture;
    public String progressScore;
    public User(){

    }
    public User(String email, String userName, String fullName, String birthDay, String gender, String phoneNumber, String address, String majors, String profilePicture, String progressScore){
        this.email = email;
        this.userName = userName;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.majors = majors;
        this.profilePicture = profilePicture;
        this.progressScore = progressScore;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getProgressScore() {
        return progressScore;
    }

    public void setProgressScore(String progressScore) {
        this.progressScore = progressScore;
    }
}
