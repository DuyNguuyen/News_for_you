package com.example.newsforyou.Class;

import java.time.LocalDateTime;
import java.util.List;

public class History {
    int id;
    LocalDateTime dateTime;
    List<Integer> newsId;
    List<Integer> userId;

    public History(int id, LocalDateTime dateTime, List<Integer> newsId, List<Integer> userId) {
        this.id = id;
        this.dateTime = dateTime;
        this.newsId = newsId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Integer> getNewsId() {
        return newsId;
    }

    public void setNewsId(List<Integer> newsId) {
        this.newsId = newsId;
    }

    public List<Integer> getUserId() {
        return userId;
    }

    public void setUserId(List<Integer> userId) {
        this.userId = userId;
    }

    public void addNews(int newsId) {
        this.newsId.add(newsId);
    }

    public void removeNews(int newsId) {
        this.newsId.remove(newsId);
    }

    public void addUser(int userId) {
        this.userId.add(userId);
    }

    public void removeUser(int userId) {
        this.userId.remove(userId);
    }
}
