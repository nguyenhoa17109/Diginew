package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private int id;
    private User user;
    private String contentCmt;
    private int like;
    private boolean answer;
    private String time;
    private boolean likeCmt;
    private int idNews;
    private int position;

    public Comment() {
    }

    public Comment(int id, User user, String contentCmt, int like, boolean answer, String time
            , boolean likeCmt, int idNews, int position) {
        this.id = id;
        this.user = user;
        this.contentCmt = contentCmt;
        this.like = like;
        this.answer = answer;
        this.time = time;
        this.likeCmt = likeCmt;
        this.idNews = idNews;
        this.position = position;
    }

    public Comment(User user, String contentCmt, int like, boolean answer, String time,
                   boolean likeCmt, int idNews, int position) {
        this.user = user;
        this.contentCmt = contentCmt;
        this.like = like;
        this.answer = answer;
        this.time = time;
        this.likeCmt = likeCmt;
        this.idNews = idNews;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
