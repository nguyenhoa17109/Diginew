package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class OtherApp implements Serializable {
    private int id;
    private String name;
    private int img;
    private int content;
    private String link;

    public OtherApp() {
    }

    public OtherApp(int id, String name, int img, int content, String link) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.content = content;
        this.link = link;
    }

    public OtherApp(String name, int img, int content, String link) {
        this.name = name;
        this.img = img;
        this.content = content;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
