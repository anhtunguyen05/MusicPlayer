package DAO;

import dbcontext.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.History;

public class HistoryDAO {

    // Lấy lịch sử nghe bài hát của người dùng
    public List<History> getHistoryByUser(int userId) {
        List<History> historyList = new ArrayList<>();
        ConnectDB db = ConnectDB.getInstance();
        String sql = "SELECT * FROM MUSICPLAYER.History WHERE user_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int historyId = rs.getInt("history_id");
                int songId = rs.getInt("song_id");
                Date listenedAt = rs.getDate("listened_at");  // Lấy giá trị Date từ cơ sở dữ liệu

                History history = new History(historyId, userId, songId, listenedAt);
                historyList.add(history);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return historyList;
    }

    // Thêm lịch sử nghe bài hát
    public void addHistory(History history) {
        String sql = "INSERT INTO History (user_id, song_id) VALUES (?, ?)";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, history.getUserId());
            statement.setInt(2, history.getSongId());
           // statement.setDate(3, history.getListenedAt());  // Cập nhật với kiểu Date
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Cập nhật lịch sử nghe bài hát (nếu cần thiết)
    public void updateHistory(History history) {
        String sql = "UPDATE MUSICPLAYER.History SET user_id=?, song_id=?, listened_at=? WHERE history_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, history.getUserId());
            statement.setInt(2, history.getSongId());
            statement.setDate(3, history.getListenedAt());  // Cập nhật với kiểu Date
            statement.setInt(4, history.getHistoryId());
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Lấy lịch sử theo ID
    public History getHistory(String idd) {
        int historyId = Integer.parseInt(idd);
        ConnectDB db = ConnectDB.getInstance();
        History history = null;
        String sql = "SELECT * FROM MUSICPLAYER.History WHERE history_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, historyId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                int songId = rs.getInt("song_id");
                Date listenedAt = rs.getDate("listened_at");  // Lấy giá trị Date từ cơ sở dữ liệu

                history = new History(historyId, userId, songId, listenedAt);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return history;
    }

    // Xóa lịch sử nghe bài hát theo ID
    public void deleteHistory(String idd) {
        try {
            ConnectDB db = ConnectDB.getInstance();
            Connection con = db.openConnection();
            String sql = "DELETE FROM MUSICPLAYER.History WHERE history_id=?";  // Cập nhật với tên cơ sở dữ liệu MUSICPLAYER
            PreparedStatement statement = con.prepareStatement(sql);
            int historyId = Integer.parseInt(idd);
            statement.setInt(1, historyId);
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
