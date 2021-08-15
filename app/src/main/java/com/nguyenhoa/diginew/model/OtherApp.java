package com.nguyenhoa.diginew.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class OtherApp implements Serializable {
    private String name;
    private int img;
    private int content;
    private String link;

    public OtherApp(String name, int img, int content, String link) {
        this.name = name;
        this.img = img;
        this.content = content;
        this.link = link;
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

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
