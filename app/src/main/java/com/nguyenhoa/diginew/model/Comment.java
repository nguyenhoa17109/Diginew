package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private User user;
    private String contentCmt;
    private int like;
    private boolean answer;
    private String time;
    private boolean likeCmt;

    public Comment() {
    }

    public Comment(User user, String contentCmt, int like, boolean answer, String time, boolean likeCmt) {
        this.user = user;
        this.contentCmt = contentCmt;
        this.like = like;
        this.answer = answer;
        this.time = time;
        this.likeCmt = likeCmt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContentCmt() {
        return contentCmt;
    }

    public void setContentCmt(String contentCmt) {
        this.contentCmt = contentCmt;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isLikeCmt() {
        return likeCmt;
    }

    public void setLikeCmt(boolean likeCmt) {
        this.likeCmt = likeCmt;
    }
}
