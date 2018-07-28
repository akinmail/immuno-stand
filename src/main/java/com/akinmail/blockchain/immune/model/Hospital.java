package com.akinmail.blockchain.immune.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.List;
import java.util.UUID;

@Document
public class Hospital {
    @Id
    private String id;
    private String name;
    private String address;
    private String location;
    private List<Child> children;

    public void generateId() {
        this.id = UUID.randomUUID().toString().replaceAll("-","");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
