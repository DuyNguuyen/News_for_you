package com.example.newsforyou.Class;

import java.time.LocalDateTime;
import java.util.List;

public class Comment {
    int id;
    String content;
    LocalDateTime createTime;
    LocalDateTime lastUpdateTime;
    List<Integer> replyId;
    List<Integer> userId;

    public Comment(int id, String content, LocalDateTime createTime, LocalDateTime lastUpdateTime, List<Integer> replyId, List<Integer> userId) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.replyId = replyId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<Integer> getReplyId() {
        return replyId;
    }

    public void setReplyId(List<Integer> replyId) {
        this.replyId = replyId;
    }

    public List<Integer> getUserId() {
        return userId;
    }

    public void setUserId(List<Integer> userId) {
        this.userId = userId;
    }

    public void addReply(int replyId) {
        this.replyId.add(replyId);
    }

    public void removeReply(int replyId) {
        this.replyId.remove(replyId);
    }

    public void addUser(int userId) {
        this.userId.add(userId);
    }

    public void removeUser(int userId) {
        this.userId.remove(userId);
    }
}
