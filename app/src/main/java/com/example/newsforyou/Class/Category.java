package com.example.newsforyou.Class;

import java.util.ArrayList;
import java.util.List;

public class Category {
    int id;
    String name;
    List<Integer> newsId;

    public Category(int id, String name, List<Integer> newsId) {
        this.id = id;
        this.name = name;
        this.newsId = newsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getNewsId() {
        return newsId;
    }

    public void setNewsId(List<Integer> newsId) {
        this.newsId = newsId;
    }

    public void addNews(int newsId) {
        this.newsId.add(newsId);
    }

    public void removeNews(int newsId) {
        this.newsId.remove(newsId);
    }
}
