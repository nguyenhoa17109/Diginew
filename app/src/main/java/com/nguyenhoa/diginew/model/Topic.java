package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class Topic implements Serializable {
    private int id;
    private String name;
    private String img;
    Boolean isSelected = false;

    public Topic() {
    }

    public Topic(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public Topic(int id, String name, String img, Boolean isSelected) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.isSelected = isSelected;
    }

    public Topic(String name, String img, Boolean isSelected) {
        this.name = name;
        this.img = img;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
