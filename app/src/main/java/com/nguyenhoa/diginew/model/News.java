package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class News implements Serializable {
    private String source;
    private int times;
    private String title;
    private int likes;
    private int cmts;
    private int imgs;
    private String content;
    private String type;

    public News(String type, String source, int times,
                String title, int likes, int cmts, int imgs, String content) {
        this.type = type;
        this.source = source;
        this.times = times;
        this.title = title;
        this.likes = likes;
        this.cmts = cmts;
        this.imgs = imgs;
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCmts() {
        return cmts;
    }

    public void setCmts(int cmts) {
        this.cmts = cmts;
    }

    public int getImgs() {
        return imgs;
    }

    public void setImgs(int imgs) {
        this.imgs = imgs;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
