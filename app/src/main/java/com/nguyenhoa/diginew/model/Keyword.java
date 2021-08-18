package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class Keyword implements Serializable {
    private int id;
    private String key;
    private int numAccess;
    private String dateSearchNear;

    public Keyword() {
    }

    public Keyword(int id, String key, int numAccess, String dateSearchNear) {
        this.id = id;
        this.key = key;
        this.numAccess = numAccess;
        this.dateSearchNear = dateSearchNear;
    }

    public Keyword(String key, int numAccess, String dateSearchNear) {
        this.key = key;
        this.numAccess = numAccess;
        this.dateSearchNear = dateSearchNear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getNumAccess() {
        return numAccess;
    }

    public void setNumAccess(int numAccess) {
        this.numAccess = numAccess;
    }

    public String getDateSearchNear() {
        return dateSearchNear;
    }

    public void setDateSearchNear(String dateSearchNear) {
        this.dateSearchNear = dateSearchNear;
    }
}
