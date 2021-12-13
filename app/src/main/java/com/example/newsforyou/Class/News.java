package com.example.newsforyou.Class;

import java.time.LocalDateTime;

public class News {
    int id;
    String title;
    String content;
    String author;
    int commentId;
    int reportCount;
    int viewCount;
    int likeCount;
    LocalDateTime dateTime;

    public News(int id, String title, String content, String author, int commentId, int reportCount, int viewCount, int likeCount, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.commentId = commentId;
        this.reportCount = reportCount;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
