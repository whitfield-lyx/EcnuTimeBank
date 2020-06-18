package com.example.ecnutimebank.entity;

public class VolunteerFor {
    private Integer volunteerForId;

    private Integer orderId;

    private Integer volunteerId;

    public VolunteerFor(Integer volunteerForId, Integer orderId, Integer volunteerId) {
        this.volunteerForId = volunteerForId;
        this.orderId = orderId;
        this.volunteerId = volunteerId;
    }

    public VolunteerFor() {
        super();
    }

    public Integer getVolunteerForId() {
        return volunteerForId;
    }

    public void setVolunteerForId(Integer volunteerForId) {
        this.volunteerForId = volunteerForId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Integer volunteerId) {
        this.volunteerId = volunteerId;
    }
}