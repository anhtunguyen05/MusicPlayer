package DAO;

import dbcontext.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Song;

public class SongDAO {

    // Lấy danh sách tất cả bài hát
    public List<Song> getSongs() {
        List<Song> songList = new ArrayList<>();
        ConnectDB db = ConnectDB.getInstance();
        String sql = "SELECT * FROM Songs";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int songId = rs.getInt("song_id");
                String songName = rs.getString("song_name");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");
                String songImg = rs.getString("song_img");
                String fileUrl = rs.getString("file_url");
                int userId = rs.getInt("user_id");
                Date uploadDate = rs.getDate("upload_date");

                Song song = new Song(songId, songName, artist, genre, songImg, fileUrl, userId, uploadDate);
                songList.add(song);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songList;
    }

    // Thêm bài hát mới
    public void addSong(Song song) {
        String sql = "INSERT INTO Songs (song_name, artist, genre, song_img, file_url, user_id, upload_date) VALUES (?, ?, ?, ?, ?, ?, ?)";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, song.getSongName());
            statement.setString(2, song.getArtist());
            statement.setString(3, song.getGenre());
            statement.setString(4, song.getSongImg());
            statement.setString(5, song.getFileUrl());
            statement.setInt(6, song.getUserId());
            statement.setDate(7, (Date) song.getUploadDate());
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Cập nhật thông tin bài hát
    public void updateSong(Song song) {
        String sql = "UPDATE Songs SET song_name=?, artist=?, genre=?, song_img=?, file_url=?, user_id=?, upload_date=? WHERE song_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, song.getSongName());
            statement.setString(2, song.getArtist());
            statement.setString(3, song.getGenre());
            statement.setString(4, song.getSongImg());
            statement.setString(5, song.getFileUrl());
            statement.setInt(6, song.getUserId());
            statement.setDate(7, (Date) song.getUploadDate());
            statement.setInt(8, song.getSongId());
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Lấy bài hát theo ID
    public Song getSongById(String idd) {
        int songId = Integer.parseInt(idd);
        ConnectDB db = ConnectDB.getInstance();
        Song song = null;
        String sql = "SELECT * FROM Songs WHERE song_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, songId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String songName = rs.getString("song_name");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");
                String songImg = rs.getString("song_img");
                String fileUrl = rs.getString("file_url");
                int userId = rs.getInt("user_id");
                Date uploadDate = rs.getDate("upload_date");

                song = new Song(songId, songName, artist, genre, songImg, fileUrl, userId, uploadDate);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return song;
    }

    // Lấy bài hát theo Name
    public List<Song> getSongsByName(String name) {
        ConnectDB db = ConnectDB.getInstance();
        List<Song> songList = new ArrayList<>();
        String sql = "SELECT * FROM Songs WHERE song_name LIKE ?"; // Tìm kiếm tên chứa 'name'

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + name + "%"); // Thêm dấu % để tìm kiếm gần đúng
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int songId = rs.getInt("song_id");
                String songName = rs.getString("song_name");
                String artist = rs.getString("artist");
                String genre = rs.getString("genre");
                String songImg = rs.getString("song_img");
                String fileUrl = rs.getString("file_url");
                int userId = rs.getInt("user_id");
                Date uploadDate = rs.getDate("upload_date");

                // Tạo đối tượng Song và thêm vào danh sách
                Song song = new Song(songId, songName, artist, genre, songImg, fileUrl, userId, uploadDate);
                songList.add(song);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songList; // Trả về danh sách bài hát
    }

    // Xóa bài hát theo ID
    public void deleteSong(String idd) {
        try {
            ConnectDB db = ConnectDB.getInstance();
            Connection con = db.openConnection();
            String sql = "EXEC DeleteSong @song_id = ?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
            PreparedStatement statement = con.prepareStatement(sql);
            int songId = Integer.parseInt(idd);
            statement.setInt(1, songId);
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
