package com.nguyenhoa.diginew.model;

import java.io.Serializable;

public class Packet implements Serializable {
    private String data;
    private String onNet;
    private String inNet;
    private String sms;
    private String myTV;
    private String clips;
    private String hotNews;
    private String music;
    private String movie;
    private String dataRemaining;

    public Packet() {
    }

    public Packet(String data, String onNet, String inNet, String sms, String myTV, String clips, String hotNews, String music, String movie, String dataRemaining) {
        this.data = data;
        this.onNet = onNet;
        this.inNet = inNet;
        this.sms = sms;
        this.myTV = myTV;
        this.clips = clips;
        this.hotNews = hotNews;
        this.music = music;
        this.movie = movie;
        this.dataRemaining = dataRemaining;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOnNet() {
        return onNet;
    }

    public void setOnNet(String onNet) {
        this.onNet = onNet;
    }

    public String getInNet() {
        return inNet;
    }

    public void setInNet(String inNet) {
        this.inNet = inNet;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getMyTV() {
        return myTV;
    }

    public void setMyTV(String myTV) {
        this.myTV = myTV;
    }

    public String getClips() {
        return clips;
    }

    public void setClips(String clips) {
        this.clips = clips;
    }

    public String getHotNews() {
        return hotNews;
    }

    public void setHotNews(String hotNews) {
        this.hotNews = hotNews;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getDataRemaining() {
        return dataRemaining;
    }

    public void setDataRemaining(String dataRemaining) {
        this.dataRemaining = dataRemaining;
    }
}
