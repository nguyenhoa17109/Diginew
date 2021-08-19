package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String imgAccount;
    private String nameUser;

    public User(String imgAccount, String nameUser) {
        this.imgAccount = imgAccount;
        this.nameUser = nameUser;
    }

    public User() {
    }

    public User(int id, String imgAccount, String nameUser) {
        this.id = id;
        this.imgAccount = imgAccount;
        this.nameUser = nameUser;
    }

    public String getImgAccount() {
        return imgAccount;
    }

    public void setImgAccount(String imgAccount) {
        this.imgAccount = imgAccount;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
