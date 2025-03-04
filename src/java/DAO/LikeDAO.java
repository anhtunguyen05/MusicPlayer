package DAO;

import dbcontext.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Like;

public class LikeDAO {

    // Lấy tất cả các lượt thích (likes) cho một bài hát
    public List<Like> getLikesForSong(int songId) {
        List<Like> likeList = new ArrayList<>();
        ConnectDB db = ConnectDB.getInstance();
        String sql = "SELECT * FROM Likes WHERE song_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, songId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int likeId = rs.getInt("like_id");
                int userId = rs.getInt("user_id");
                Date createdAt = rs.getDate("created_at");  // Lấy giá trị Date từ cơ sở dữ liệu

                Like like = new Like(likeId, songId, userId, createdAt);
                likeList.add(like);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(LikeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return likeList;
    }

    // Thêm một lượt thích mới
    public void addLike(Like like) {
        String sql = "INSERT INTO Likes (song_id, user_id, created_at) VALUES (?, ?, ?)";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, like.getSongId());
            statement.setInt(2, like.getUserId());
            statement.setDate(3, like.getCreatedAt());  // Cập nhật với kiểu Date
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(LikeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Cập nhật thông tin lượt thích (Nếu có trường hợp cần thiết)
    public void updateLike(Like like) {
        String sql = "UPDATE Likes SET song_id=?, user_id=?, created_at=? WHERE like_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, like.getSongId());
            statement.setInt(2, like.getUserId());
            statement.setDate(3, like.getCreatedAt());  // Cập nhật với kiểu Date
            statement.setInt(4, like.getLikeId());
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(LikeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Lấy lượt thích theo ID
    public Like getLike(String idd) {
        int likeId = Integer.parseInt(idd);
        ConnectDB db = ConnectDB.getInstance();
        Like like = null;
        String sql = "SELECT * FROM Likes WHERE like_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, likeId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int songId = rs.getInt("song_id");
                int userId = rs.getInt("user_id");
                Date createdAt = rs.getDate("created_at");  // Lấy giá trị Date từ cơ sở dữ liệu

                like = new Like(likeId, songId, userId, createdAt);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(LikeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return like;
    }

    // Xóa lượt thích theo ID
    public void deleteLike(String idd) {
        try {
            ConnectDB db = ConnectDB.getInstance();
            Connection con = db.openConnection();
            String sql = "DELETE FROM Likes WHERE like_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
            PreparedStatement statement = con.prepareStatement(sql);
            int likeId = Integer.parseInt(idd);
            statement.setInt(1, likeId);
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(LikeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
