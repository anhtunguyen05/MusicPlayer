package model;

import java.sql.Date;

public class History {
    private int historyId;
    private int userId;
    private int songId;
    private Date listenedAt;  // Kiểu Date thay vì DATETIME

    // Constructor
    public History(int historyId, int userId, int songId, Date listenedAt) {
        this.historyId = historyId;
        this.userId = userId;
        this.songId = songId;
        this.listenedAt = listenedAt;
    }

    public History( int userId, int songId) {
        this.userId = userId;
        this.songId = songId;
    }
    
    // Getters and Setters
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public Date getListenedAt() {
        return listenedAt;
    }

    public void setListenedAt(Date listenedAt) {
        this.listenedAt = listenedAt;
    }

    @Override
    public String toString() {
        return "History{" + "historyId=" + historyId + ", userId=" + userId + ", songId=" + songId + ", listenedAt=" + listenedAt + '}';
    }
    
}
