package com.example.ecnutimebank.entity;

public class Facility {
    private Integer facilityId;

    private String facilityName;

    private String facilityAddress;

    private String facilityDescription;

    public Facility(Integer facilityId, String facilityName, String facilityAddress, String facilityDescription) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.facilityAddress = facilityAddress;
        this.facilityDescription = facilityDescription;
    }

    public Facility() {
        super();
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName == null ? null : facilityName.trim();
    }

    public String getFacilityAddress() {
        return facilityAddress;
    }

    public void setFacilityAddress(String facilityAddress) {
        this.facilityAddress = facilityAddress == null ? null : facilityAddress.trim();
    }

    public String getFacilityDescription() {
        return facilityDescription;
    }

    public void setFacilityDescription(String facilityDescription) {
        this.facilityDescription = facilityDescription == null ? null : facilityDescription.trim();
    }
}