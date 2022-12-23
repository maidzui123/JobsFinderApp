package com.example.jobfinderapp;

import android.net.Uri;

import java.util.List;

public class User {
    public String email, userName, fullName, birthDay, gender, phoneNumber, address, majors, profilePicture, rateStar, feedBack, hobbies;
    public List<String> jobApplied;
    public int progressScore;
    public User(){

    }

    public User(String email, String userName, String fullName, String birthDay, String gender, String phoneNumber, String address, String majors, String profilePicture, String rateStar, String feedBack, String hobbies, List<String> jobApplied, int progressScore){
        this.email = email;
        this.userName = userName;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.majors = majors;
        this.profilePicture = profilePicture;
        this.rateStar = rateStar;
        this.feedBack = feedBack;
        this.hobbies = hobbies;
        this.jobApplied = jobApplied;
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

    public String getRateStar() {
        return rateStar;
    }

    public void setRateStar(String rateStar) {
        this.rateStar = rateStar;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public List<String> getJobApplied() {
        return jobApplied;
    }

    public void setJobApplied(List<String> jobApplied) {
        this.jobApplied = jobApplied;
    }

    public int getProgressScore() {
        return progressScore;
    }

    public void setProgressScore(int progressScore) {
        this.progressScore = progressScore;
    }
}

