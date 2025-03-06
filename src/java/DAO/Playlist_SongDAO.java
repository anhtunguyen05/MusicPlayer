package DAO;

import dbcontext.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Song;

public class Playlist_SongDAO {
    
    // Thêm bài hát vào playlist
    public void addSongToPlaylist(int playlistId, int songId) {
        String sql = "INSERT INTO Playlist_Songs (playlist_id, song_id) VALUES (?, ?)";
        ConnectDB db = ConnectDB.getInstance();
        
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistId);
            statement.setInt(2, songId);
            statement.execute();
            
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(Playlist_SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Xóa bài hát khỏi playlist
    public void removeSongFromPlaylist(int playlistId, int songId) {
        String sql = "DELETE FROM Playlist_Songs WHERE playlist_id = ? AND song_id = ?";
        ConnectDB db = ConnectDB.getInstance();
        
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistId);
            statement.setInt(2, songId);
            statement.execute();
            
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(Playlist_SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Lấy danh sách bài hát trong playlist
    public List<Song> getSongsInPlaylist(int playlistId) {
        List<Song> songList = new ArrayList<>();
        ConnectDB db = ConnectDB.getInstance();
        String sql = "SELECT s.* FROM Songs s JOIN Playlist_Songs ps ON s.song_id = ps.song_id WHERE ps.playlist_id = ?";
        
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistId);
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
            Logger.getLogger(Playlist_SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songList;
    }
    
    // Kiểm tra xem một bài hát có tồn tại trong playlist không
    public boolean isSongInPlaylist(int playlistId, int songId) {
        String sql = "SELECT COUNT(*) FROM Playlist_Songs WHERE playlist_id = ? AND song_id = ?";
        ConnectDB db = ConnectDB.getInstance();
        boolean exists = false;
        
        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, playlistId);
            statement.setInt(2, songId);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next() && rs.getInt(1) > 0) {
                exists = true;
            }
            
            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(Playlist_SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }
}
