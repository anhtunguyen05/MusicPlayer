package DAO;

import dbcontext.ConnectDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDAO {

    // Lấy danh sách tất cả người dùng
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        ConnectDB db = ConnectDB.getInstance();
        String sql = "SELECT * FROM Users";

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String passwordHash = rs.getString("password_hash");
                String avatarUrl = rs.getString("avatar_url");
                boolean isVip = rs.getBoolean("is_vip");
                String verificationCode = rs.getString("verification_code");
                boolean isVerified = rs.getBoolean("is_verified");

                User user = new User(userId, username, email, passwordHash, avatarUrl, isVip, verificationCode, isVerified);
                userList.add(user);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    // Thêm người dùng mới
    public void addUser(User user) {
        String sql = "INSERT INTO Users(username, email, password_hash, avatar_url, is_vip, verification_code, is_verified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getAvatarUrl());
            statement.setBoolean(5, user.isVip());
            statement.setString(6, user.getVerificationCode());
            statement.setBoolean(7, user.isVerified());
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Cập nhật thông tin người dùng
    public void updateUser(User user) {
        String sql = "UPDATE Users SET username=?, email=?, password_hash=?, avatar_url=?, is_vip=?, verification_code=?, is_verified=? WHERE user_id=?";
        ConnectDB db = ConnectDB.getInstance();

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPasswordHash());
            statement.setString(4, user.getAvatarUrl());
            statement.setBoolean(5, user.isVip());
            statement.setString(6, user.getVerificationCode());
            statement.setBoolean(7, user.isVerified());
            statement.setInt(8, user.getUserId());
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Lấy người dùng theo ID
    public User getUserById(String idd) {
        int userId = Integer.parseInt(idd);
        ConnectDB db = ConnectDB.getInstance();
        User user = null;
        String sql = "SELECT * FROM Users WHERE user_id=?";

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String passwordHash = rs.getString("password_hash");
                String avatarUrl = rs.getString("avatar_url");
                boolean isVip = rs.getBoolean("is_vip");
                String verificationCode = rs.getString("verification_code");
                boolean isVerified = rs.getBoolean("is_verified");

                user = new User(userId, username, email, passwordHash, avatarUrl, isVip, verificationCode, isVerified);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    // Lấy người dùng theo tên
    public List<User> getUsersByName(String name) {
        ConnectDB db = ConnectDB.getInstance();
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE username LIKE ?"; // Tìm kiếm gần đúng theo tên

        try {
            Connection con = db.openConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + name + "%"); // Thêm % để tìm kiếm gần đúng
            ResultSet rs = statement.executeQuery();

            while (rs.next()) { // Lặp qua tất cả các kết quả tìm được
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String passwordHash = rs.getString("password_hash");
                String avatarUrl = rs.getString("avatar_url");
                boolean isVip = rs.getBoolean("is_vip");
                String verificationCode = rs.getString("verification_code");
                boolean isVerified = rs.getBoolean("is_verified");

                // Tạo đối tượng User và thêm vào danh sách
                User user = new User(userId, username, email, passwordHash, avatarUrl, isVip, verificationCode, isVerified);
                userList.add(user);
            }

            rs.close();
            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList; // Trả về danh sách user
    }

    // Xóa người dùng theo ID
    public void deleteUser(String idd) {
        try {
            ConnectDB db = ConnectDB.getInstance();
            Connection con = db.openConnection();
            String sql = "DELETE FROM Users WHERE user_id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            int userId = Integer.parseInt(idd);
            statement.setInt(1, userId);
            statement.execute();

            statement.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
