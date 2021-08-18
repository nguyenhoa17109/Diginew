package com.nguyenhoa.diginew.model;

public class NSave implements Comparable<NSave>{
    public News news;

    public NSave(News news) {
        this.news = news;
    }

    @Override
    public int compareTo(NSave nSave) {
        return (this.news.getDateSave().compareTo(nSave.news.getDateSave()));
    }
}