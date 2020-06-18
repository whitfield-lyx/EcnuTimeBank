package com.example.ecnutimebank.helper;

public class AppConst {
    public static final String SERVER_ADDRESS = "http://10.0.2.2:8080"; //模拟器访问本地服务器地址 (模拟器认为localhost是自己 原来的127.0.0.1 要用10.0.2.2代替
    public interface User{
        String login = SERVER_ADDRESS+"/api/user/login";
        String register = SERVER_ADDRESS+"/api/user/register";
        String get_all_user= SERVER_ADDRESS+"/api/user";
        String get_user= SERVER_ADDRESS+"/api/user"; //+ /{userID}
        String select_user_by_condition= SERVER_ADDRESS+"/api/user/search";
        String delete_user = SERVER_ADDRESS+"/api/user"; //+ /{userID}
        String update_user = SERVER_ADDRESS+"/api/user/update"; //传整个User 若属性为null不会修改
    }

    public interface Order {
        String publish_new_order = SERVER_ADDRESS+"/api/order";
        String get_10_more_order = SERVER_ADDRESS + "/api/order";
        String get_10_more_order_by_type = SERVER_ADDRESS + "/api/order/type/"; // 示例 /api/order/type/0/offset/0
        String get_10_more_accepted_order = SERVER_ADDRESS + "/api/order/accepted/"; // /api/order/accepted/userId/offset/0
        String get_10_more_published_order = SERVER_ADDRESS + "/api/order/published/"; // /api/order/published/userId/offset/0
        String[] type_name = new String[]{"全部", "陪聊", "散步", "代买", "打扫", "其他"};
        String update_order = SERVER_ADDRESS+"/api/order";
    }

    public interface Facility{
        String get_all_facility= SERVER_ADDRESS+"/api/facility"; //
        String get_facility= SERVER_ADDRESS+"/api/facility"; //+ /{facilityID}
        String select_facility_by_ID= SERVER_ADDRESS+"/api/facility";
        String delete_facility = SERVER_ADDRESS+"/api/facility"; //+ /{facilityID}
        String update_facility = SERVER_ADDRESS+"/api/facility/update"; //传整个facility 若属性为null不会修改
    }
}
