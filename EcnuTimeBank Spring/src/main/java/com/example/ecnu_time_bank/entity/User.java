package com.example.ecnu_time_bank.entity;

public class User {
    private Integer userId;

    private String userName;

    private String userType;

    private String userPassword;

    private String userPhoto;

    public User(Integer userId, String userName, String userType, String userPassword, String userPhoto) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.userPassword = userPassword;
        this.userPhoto = userPhoto;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto == null ? null : userPhoto.trim();
    }
}