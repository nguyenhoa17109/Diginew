package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class NLike implements Comparable<NLike>{
    public News news;

    public NLike(News news) {
        this.news = news;
    }

    @Override
    public int compareTo(NLike nLike) {
        return (this.news.getDateLike().compareTo(nLike.news.getDateLike()));
    }
}
