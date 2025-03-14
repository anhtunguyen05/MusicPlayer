package DAO;

import dbcontext.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Playlist;

public class PlaylistDAO {

    // Lấy danh sách tất cả playlist
    public List<Playlist> getPlaylists() {
        List<Playlist> playlistList = new ArrayList<>();
        ConnectDB db = ConnectDB.getInstance();
        String sql = "SELECT * Playlists";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int playlistId = rs.getInt("playlist_id");
                String playlistName = rs.getString("playlist_name");
                String playlistImg = rs.getString("playlist_img");
                int userId = rs.getInt("user_id");
                Date createdAt = rs.getDate("created_at");

                Playlist playlist = new Playlist(playlistId, playlistName, playlistImg, userId, createdAt);
                playlistList.add(playlist);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlistList;
    }

    public List<Playlist> getPlaylistsHomepage(){
        List<Playlist> playlistList = new ArrayList<>();
        ConnectDB db = ConnectDB.getInstance();
        String sql = "SELECT p.playlist_id, p.playlist_name, p.playlist_img, COUNT(ps.song_id) as song_count " +
                     "FROM Playlists p LEFT JOIN Playlist_Songs ps ON p.playlist_id = ps.playlist_id " +
                     "GROUP BY p.playlist_id, p.playlist_name, p.playlist_img ";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int playlistId = rs.getInt("playlist_id");
                String playlistName = rs.getString("playlist_name");
                String playlistImg = rs.getString("playlist_img");
                int songCount = rs.getInt("song_count");

                Playlist playlist = new Playlist(playlistId, playlistName, playlistImg, songCount);
                playlistList.add(playlist);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlistList;
    }
    
    
    
    // Thêm playlist mới
    public void addPlaylist(Playlist playlist) {
        String sql = "INSERT INTO Playlists (playlist_name, playlist_img, user_id, created_at) VALUES (?, ?, ?, ?)";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, playlist.getPlaylistName());
            statement.setString(2, playlist.getPlaylistImg());
            statement.setInt(3, playlist.getUserId());
            statement.setDate(4, (Date) playlist.getCreatedAt());
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Cập nhật thông tin playlist
    public void updatePlaylist(Playlist playlist) {
        String sql = "UPDATE Playlists SET playlist_name=?, playlist_img=?, user_id=?, created_at=? WHERE playlist_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, playlist.getPlaylistName());
            statement.setString(2, playlist.getPlaylistImg());
            statement.setInt(3, playlist.getUserId());
            statement.setDate(4, (Date) playlist.getCreatedAt());
            statement.setInt(5, playlist.getPlaylistId());
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Playlist> getPlaylistsByUserId(int userId) {
        List<Playlist> playlistList = new ArrayList<>();
        ConnectDB db = ConnectDB.getInstance();

        String sql = "SELECT p.playlist_id, p.playlist_name, p.playlist_img, COUNT(ps.song_id) as song_count " +
                     "FROM Playlists p " +
                     "LEFT JOIN Playlist_Songs ps ON p.playlist_id = ps.playlist_id " +
                     "WHERE p.user_id = ? " +  // Lọc theo user_id
                     "GROUP BY p.playlist_id, p.playlist_name, p.playlist_img";

        try (Connection con = db.openConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int playlistId = rs.getInt("playlist_id");
                String playlistName = rs.getString("playlist_name");
                String playlistImg = rs.getString("playlist_img");
                int songCount = rs.getInt("song_count");

                Playlist playlist = new Playlist(playlistId, playlistName, playlistImg, songCount);
                playlistList.add(playlist);
            }

        } catch (Exception ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlistList;
    }

    // Lấy playlist theo ID
    public Playlist getPlaylistById(String idd) {
        int playlistId = Integer.parseInt(idd);
        ConnectDB db = ConnectDB.getInstance();
        Playlist playlist = null;
        String sql = "SELECT * FROM Playlists WHERE playlist_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String playlistName = rs.getString("playlist_name");
                String playlistImg = rs.getString("playlist_img");
                int userId = rs.getInt("user_id");
                Date createdAt = rs.getDate("created_at");

                playlist = new Playlist(playlistId, playlistName, playlistImg, userId, createdAt);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlist;
    }
    
    // Lấy playlist theo tên
    public List<Playlist> getPlaylistByName(String name) {
        ConnectDB db = ConnectDB.getInstance();
        List<Playlist> playlistList = new ArrayList<>();
        String sql = "SELECT * FROM Playlists WHERE playlist_name LIKE ?"; // Tìm kiếm theo tên playlist gần đúng

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + name + "%"); // Thêm % để tìm kiếm gần đúng
            ResultSet rs = statement.executeQuery();

            while (rs.next()) { // Lặp qua tất cả kết quả tìm được
                int playlistId = rs.getInt("playlist_id");
                String playlistName = rs.getString("playlist_name");
                String playlistImg = rs.getString("playlist_img");
                int userId = rs.getInt("user_id");
                Date createdAt = rs.getDate("created_at");

                // Tạo đối tượng Playlist và thêm vào danh sách
                Playlist playlist = new Playlist(playlistId, playlistName, playlistImg, userId, createdAt);
                playlistList.add(playlist);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playlistList; // Trả về danh sách playlist
    }

    // Xóa playlist theo ID
    public void deletePlaylist(String idd) {
        try {
            ConnectDB db = ConnectDB.getInstance();
            Connection con = db.openConnection();
            String sql = "DELETE Playlists WHERE playlist_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
            PreparedStatement statement = con.prepareStatement(sql);
            int playlistId = Integer.parseInt(idd);
            statement.setInt(1, playlistId);
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(PlaylistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
