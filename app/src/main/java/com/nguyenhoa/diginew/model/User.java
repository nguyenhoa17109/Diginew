package com.nguyenhoa.diginew.model;

import android.widget.ImageView;

public class User {
    private String username;
    private String name;
    private String address;
    private String dob;
    private String phoneNum;
    private String servicePack;
    //private ImageView img;

    public User() {

    }

    public User(String username, String name, String address, String dob, String phoneNum, String servicePack) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.phoneNum = phoneNum;
        this.servicePack = servicePack;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getServicePack() {
        return servicePack;
    }

    public void setServicePack(String servicePack) {
        this.servicePack = servicePack;
    }
}
