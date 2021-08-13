package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class Operation implements Serializable, Comparable<Operation> {
    private News news;
    private String date;
    private boolean save;
    private boolean download;
    private boolean like;

    public Operation() {
    }

    public Operation(News news, String date, boolean save, boolean download, boolean like) {
        this.news = news;
        this.date = date;
        this.save = save;
        this.download = download;
        this.like = like;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    @Override
    public int compareTo(Operation operation) {
        return (this.getDate().compareTo(operation.getDate()));
    }

}
