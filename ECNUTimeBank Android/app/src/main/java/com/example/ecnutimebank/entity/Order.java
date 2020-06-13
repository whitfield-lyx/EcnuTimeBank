package com.example.ecnutimebank.entity;

import java.sql.Timestamp;

public class Order {
    private Integer orderId;

    private String orderTitle;

    private Timestamp orderPubleshedTime;

    private String orderDescription;

    private String orderType;

    private String orderTime;

    private String orderAddress;

    private String orderTelephone;

    private Integer orderBonus;

    private Integer orderPublisherId;

    private Integer orderAccpetersId;

    private Integer orderEmployeeListId;

    private String orderState;

    public Order(Integer orderId, String orderTitle, Timestamp orderPubleshedTime, String orderDescription, String orderType, String orderTime, String orderAddress, String orderTelephone, Integer orderBonus, Integer orderPublisherId, Integer orderAccpetersId, Integer orderEmployeeListId, String orderState) {
        this.orderId = orderId;
        this.orderTitle = orderTitle;
        this.orderPubleshedTime = orderPubleshedTime;
        this.orderDescription = orderDescription;
        this.orderType = orderType;
        this.orderTime = orderTime;
        this.orderAddress = orderAddress;
        this.orderTelephone = orderTelephone;
        this.orderBonus = orderBonus;
        this.orderPublisherId = orderPublisherId;
        this.orderAccpetersId = orderAccpetersId;
        this.orderEmployeeListId = orderEmployeeListId;
        this.orderState = orderState;
    }

    public Order() {
        super();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle == null ? null : orderTitle.trim();
    }

    public Timestamp getOrderPubleshedTime() {
        return orderPubleshedTime;
    }

    public void setOrderPubleshedTime(Timestamp orderPubleshedTime) {
        this.orderPubleshedTime = orderPubleshedTime;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription == null ? null : orderDescription.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime == null ? null : orderTime.trim();
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress == null ? null : orderAddress.trim();
    }

    public String getOrderTelephone() {
        return orderTelephone;
    }

    public void setOrderTelephone(String orderTelephone) {
        this.orderTelephone = orderTelephone == null ? null : orderTelephone.trim();
    }

    public Integer getOrderBonus() {
        return orderBonus;
    }

    public void setOrderBonus(Integer orderBonus) {
        this.orderBonus = orderBonus;
    }

    public Integer getOrderPublisherId() {
        return orderPublisherId;
    }

    public void setOrderPublisherId(Integer orderPublisherId) {
        this.orderPublisherId = orderPublisherId;
    }

    public Integer getOrderAccpetersId() {
        return orderAccpetersId;
    }

    public void setOrderAccpetersId(Integer orderAccpetersId) {
        this.orderAccpetersId = orderAccpetersId;
    }

    public Integer getOrderEmployeeListId() {
        return orderEmployeeListId;
    }

    public void setOrderEmployeeListId(Integer orderEmployeeListId) {
        this.orderEmployeeListId = orderEmployeeListId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }
}