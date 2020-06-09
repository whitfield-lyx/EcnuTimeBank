package com.example.ecnutimebank.helper;

public class AppConst {
    public static final String SERVER_ADDRESS = "http://10.0.2.2:8080"; //模拟器访问本地服务器地址 (模拟器认为localhost是自己 原来的127.0.0.1 要用10.0.2.2代替
    public interface User{
        String login = SERVER_ADDRESS+"/api/user/login";
        String logout = SERVER_ADDRESS+"/api/user/logout";
        String register = SERVER_ADDRESS+"/api/user/register";
        String get_user_info = SERVER_ADDRESS+"/api/user";
    }

}
