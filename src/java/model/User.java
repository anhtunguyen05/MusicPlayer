/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class User {
    private int userId;
    private String username;
    private String email;
    private String passwordHash;
    private String avatarUrl;
    private boolean isVip;
    private String verificationCode;
    private boolean isVerified;

    // Constructor
    public User(int userId, String username, String email, String passwordHash, String avatarUrl,
                boolean isVip, String verificationCode, boolean isVerified) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.avatarUrl = avatarUrl;
        this.isVip = isVip;
        this.verificationCode = verificationCode;
        this.isVerified = isVerified;
    }
    
    public User(String username, String email, String passwordHash, String avatarUrl,
                boolean isVip, String verificationCode, boolean isVerified) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.avatarUrl = avatarUrl;
        this.isVip = isVip;
        this.verificationCode = verificationCode;
        this.isVerified = isVerified;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean isVip) {
        this.isVip = isVip;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", email=" + email + ", passwordHash=" + passwordHash + ", avatarUrl=" + avatarUrl + ", isVip=" + isVip + ", verificationCode=" + verificationCode + ", isVerified=" + isVerified + '}';
    }
    
}

