package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class Topic implements Serializable {
    private String name;
    private int img;
    Boolean isSelected = false;

    public Topic(String name, int img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
