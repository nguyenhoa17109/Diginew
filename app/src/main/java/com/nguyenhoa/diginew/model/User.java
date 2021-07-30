package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class User implements Serializable {
    private int imgAccount;
    private String nameUser;

    public User(int imgAccount, String nameUser) {
        this.imgAccount = imgAccount;
        this.nameUser = nameUser;
    }

    public User() {
    }

    public int getImgAccount() {
        return imgAccount;
    }

    public void setImgAccount(int imgAccount) {
        this.imgAccount = imgAccount;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
