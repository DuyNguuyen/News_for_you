package com.example.newsforyou.Class;

import java.time.LocalDateTime;
import java.util.List;

public class Save {
    int id;
    List<Integer> newsId;
    List<Integer> userId;

    public Save(int id, List<Integer> newsId, List<Integer> userId) {
        this.id = id;
        this.newsId = newsId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
