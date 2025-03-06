/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author nguye
 */

public class Song {
    private int songId;
    private String songName;
    private String artist;
    private String genre;
    private String songImg;
    private String fileUrl;
    private int userId;
    private Date uploadDate;

    // Constructor
    public Song(int songId, String songName, String artist, String genre, String songImg, String fileUrl, int userId, Date uploadDate) {
        this.songId = songId;
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.songImg = songImg;
        this.fileUrl = fileUrl;
        this.userId = userId;
        this.uploadDate = uploadDate;
    }

    public Song( String songName, String artist, String genre, String songImg, String fileUrl, int userId, Date uploadDate) {
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.songImg = songImg;
        this.fileUrl = fileUrl;
        this.userId = userId;
        this.uploadDate = uploadDate;
    }
    
    // Getters and Setters
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSongImg() {
        return songImg;
    }

    public void setSongImg(String songImg) {
        this.songImg = songImg;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "Song{" + "songId=" + songId + ", songName=" + songName + ", artist=" + artist + ", genre=" + genre + ", songImg=" + songImg + ", fileUrl=" + fileUrl + ", userId=" + userId + ", uploadDate=" + uploadDate + '}';
    }
    
}

