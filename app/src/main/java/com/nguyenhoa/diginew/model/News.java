package com.nguyenhoa.diginew.model;

import java.io.Serializable;
import java.util.ArrayList;

public class News implements Serializable, Comparable<News> {
    private int id;
    private String source;
    private String times;
    private String title;
    private int likes;
    private int cmts;
    private String imgs;
    private String content;
    private String type;
    private Topic topic;
    private String url;
    private String audio;
    private Province province;
    private ArrayList<Comment> listComment;
    private ArrayList<Tag> listTag;
    private String dateSave;
    private String dateDown;
    private String dateLike;
    private boolean like;

    public News() {
    }

    public News(int id, String source, String times, String title, int likes, int cmts, String imgs,
                String content, String type, Topic topic, String url, String audio,
                Province province, ArrayList<Comment> listComment, ArrayList<Tag> listTag,
                String dateSave, String dateDown, String dateLike, boolean like) {
        this.id = id;
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
        this.province = province;
        this.listComment = listComment;
        this.listTag = listTag;
        this.dateSave = dateSave;
        this.dateDown = dateDown;
        this.dateLike = dateLike;
        this.like = like;
    }

    public News(String source, String times, String title, int likes, int cmts, String imgs,
                String content, String type, Topic topic, String url, String audio,
                Province province, ArrayList<Comment> listComment, ArrayList<Tag> listTag,
                String dateSave, String dateDown, String dateLike, boolean like) {
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
        this.province = province;
        this.listComment = listComment;
        this.listTag = listTag;
        this.dateSave = dateSave;
        this.dateDown = dateDown;
        this.dateLike = dateLike;
        this.like = like;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
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

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
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

    public ArrayList<Tag> getListTag() {
        return listTag;
    }

    public void setListTag(ArrayList<Tag> listTag) {
        this.listTag = listTag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateSave() {
        return dateSave;
    }

    public void setDateSave(String dateSave) {
        this.dateSave = dateSave;
    }

    public String getDateDown() {
        return dateDown;
    }

    public void setDateDown(String dateDown) {
        this.dateDown = dateDown;
    }

    public String getDateLike() {
        return dateLike;
    }

    public void setDateLike(String dateLike) {
        this.dateLike = dateLike;
    }

    @Override
    public String toString() {
        return "News{" +
                "source='" + source + '\'' +
                ", times='" + times + '\'' +
                ", title='" + title + '\'' +
                ", likes=" + likes +
                ", cmts=" + cmts +
                ", imgs='" + imgs + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", topic=" + topic +
                ", url='" + url + '\'' +
                ", audio='" + audio + '\'' +
                ", province=" + province +
                ", listComment=" + listComment +
                ", listTag=" + listTag +
                ", dateSave='" + dateSave + '\'' +
                ", dateDown='" + dateDown + '\'' +
                ", dateLike='" + dateLike + '\'' +
                ", like=" + like +
                '}';
    }

    @Override
    public int compareTo(News news) {
        return (this.getDateDown().compareTo(news.getDateDown()));
    }
}
