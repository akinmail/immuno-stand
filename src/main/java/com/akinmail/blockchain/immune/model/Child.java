package com.akinmail.blockchain.immune.model;

import org.springframework.data.mongodb.core.mapping.Document;

public class Child {
    private String childName;
    private String motherName;
    private String dob;
    private Long phoneNumber;
    private String detailsHash;

    public void generateDetailsHash(){
        this.detailsHash = String.valueOf(phoneNumber) + childName.replace(" ", "");
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDetailsHash() {
        return detailsHash;
    }

    public void setDetailsHash(String detailsHash) {
        this.detailsHash = detailsHash;
    }
}
