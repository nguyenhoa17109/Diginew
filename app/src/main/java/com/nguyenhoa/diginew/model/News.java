package com.nguyenhoa.diginew.model;

import java.io.Serializable;
import java.util.ArrayList;

public class News implements Serializable {
    private String source;
    private String times;
    private String title;
    private int likes;
    private int cmts;
    private int imgs;
    private String content;
    private String type;
    private String topic;
    private String url;
    private String audio;
    private ArrayList<Comment> listComment;
    private ArrayList<String> listTag;

    public News() {
    }

    public News(String topic, String type, String source, String times,
                String title, int likes, int cmts, int imgs, String content) {
        this.topic = topic;
        this.type = type;
        this.source = source;
        this.times = times;
        this.title = title;
        this.likes = likes;
        this.cmts = cmts;
        this.imgs = imgs;
        this.content = content;
    }

    public News(String source, String times, String title, int likes, int cmts, int imgs,
                String content, String type, String topic, String url, ArrayList<String> listTag) {
        this.source = source;
        this.times = times;
        this.title = title;
        this.likes = likes;
        this.cmts = cmts;
        this.imgs = imgs;
        this.content = content;
        this.type = type;
        this.topic = topic;
        this.url = url;
        this.listTag = listTag;
    }

    public News(String source, String times, String title, int likes, int cmts,
                int imgs, String content, String type, String topic, String url, String audio,
                ArrayList<Comment> listComment) {
        this.source = source;
        this.times = times;
        this.title = title;
        this.likes = likes;
        this.cmts = cmts;
        this.imgs = imgs;
        this.content = content;
        this.type = type;
        this.topic = topic;
        this.url = url;
        this.audio = audio;
        this.listComment = listComment;
    }

    public News(String source, String times, String title, int likes, int cmts, int imgs,
                String content, String type, String topic, String url, String audio,
                ArrayList<Comment> listComment, ArrayList<String> listTag) {
        this.source = source;
        this.times = times;
        this.title = title;
        this.likes = likes;
        this.cmts = cmts;
        this.imgs = imgs;
        this.content = content;
        this.type = type;
        this.topic = topic;
        this.url = url;
        this.audio = audio;
        this.listComment = listComment;
        this.listTag = listTag;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public ArrayList<Comment> getListComment() {
        return listComment;
    }

    public void setListComment(ArrayList<Comment> listComment) {
        this.listComment = listComment;
    }

    public ArrayList<String> getListTag() {
        return listTag;
    }

    public void setListTag(ArrayList<String> listTag) {
        this.listTag = listTag;
    }

    @Override
    public String toString() {
        return content +" "+ times +" "+title+" "+source;
    }
}
