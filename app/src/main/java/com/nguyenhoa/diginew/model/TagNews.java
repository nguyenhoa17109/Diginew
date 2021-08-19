package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class TagNews implements Serializable {
    private int id;
    private int idNews;
    private int idTag;

    public TagNews() {
    }

    public TagNews(int idNews, int idTag) {
        this.idNews = idNews;
        this.idTag = idTag;
    }

    public TagNews(int id, int idNews, int idTag) {
        this.id = id;
        this.idNews = idNews;
        this.idTag = idTag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public int getIdTag() {
        return idTag;
    }

    public void setIdTag(int idTag) {
        this.idTag = idTag;
    }
}
