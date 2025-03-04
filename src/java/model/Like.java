package model;

import java.sql.Date;

public class Like {
    private int likeId;
    private int songId;
    private int userId;
    private Date createdAt;  // Thay đổi kiểu dữ liệu thành Date

    // Constructor
    public Like(int likeId, int songId, int userId, Date createdAt) {
        this.likeId = likeId;
        this.songId = songId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Like{" + "likeId=" + likeId + ", songId=" + songId + ", userId=" + userId + ", createdAt=" + createdAt + '}';
    }
    
    
}
