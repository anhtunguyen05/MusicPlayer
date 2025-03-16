package model;

import java.util.Date;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private String playlistImg;
    private int userId;
    private Date createdAt;
    private int songNum;

    // Constructor
    public Playlist(int playlistId, String playlistName, String playlistImg, int userId, Date createdAt) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.playlistImg = playlistImg;
        this.userId = userId;
        this.createdAt = createdAt;
    }

     public Playlist( String playlistName, String playlistImg, int userId, Date createdAt) {
        this.playlistName = playlistName;
        this.playlistImg = playlistImg;
        this.userId = userId;
        this.createdAt = createdAt;
    }
    
     public Playlist(int playlistId, String playlistName, String playlistImg,  int songNum) {
         this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.playlistImg = playlistImg;
        this.songNum = songNum;
    }
     
    // Getters and Setters
    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistImg() {
        return playlistImg;
    }

    public void setPlaylistImg(String playlistImg) {
        this.playlistImg = playlistImg;
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

    public int getSongNum() {
        return songNum;
    }

    public void setSongNum(int songNum) {
        this.songNum = songNum;
    }

    @Override
    public String toString() {
        return "Playlist{" + "playlistId=" + playlistId + ", playlistName=" + playlistName + ", playlistImg=" + playlistImg + ", userId=" + userId + ", createdAt=" + createdAt + ", songNum=" + songNum + '}';
    }

   
    
}
