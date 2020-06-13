package com.example.ecnutimebank.entity;

public class User {
    private Integer userId;

    private String userName;

    private String userType;

    private String userPassword;

    private String userTelephone;

    private String userGender;

    private Double userBalance;

    private String userStudentId;

    public User(Integer userId, String userName, String userType, String userPassword, String userTelephone, String userGender, Double userBalance, String userStudentId) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.userPassword = userPassword;
        this.userTelephone = userTelephone;
        this.userGender = userGender;
        this.userBalance = userBalance;
        this.userStudentId = userStudentId;
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

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone == null ? null : userTelephone.trim();
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender == null ? null : userGender.trim();
    }

    public Double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Double userBalance) {
        this.userBalance = userBalance;
    }

    public String getUserStudentId() {
        return userStudentId;
    }

    public void setUserStudentId(String userStudentId) {
        this.userStudentId = userStudentId == null ? null : userStudentId.trim();
    }
}