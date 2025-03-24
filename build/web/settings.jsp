<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User, DAO.UserDAO" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Settings</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="./assets/css/settings.css">
        <link rel="stylesheet" href="./assets/css/set.css">
        <script src="js/theme.js"></script>

        <script>
    function togglePasswordFields() {
        var passwordFields = document.getElementById("passwordFields");
        var changePasswordBtn = document.getElementById("changePasswordBtn");
        var saveChangesBtn = document.getElementById("saveChangesBtn");

        if (passwordFields.style.display === "none") {
            passwordFields.style.display = "block";
            changePasswordBtn.style.display = "none"; // Ẩn nút "Change Password"
            saveChangesBtn.style.display = "block";   // Hiện nút "Save Changes"
        }
    }
   
</script>
    </head>
    <body>
        <%@include file="/include/header.jsp"%>

        <div class="container mt-4">
            <h2>Settings</h2>

            <%
                // Lấy user từ session
                String userID = (String) session.getAttribute("user_id");
                if (userID == null) {
                    response.sendRedirect("login.jsp");
                    return;
                }
                UserDAO userDao = new UserDAO();
                User user = userDao.getUserById(userID);
            %>

            <form action="SettingsServlet" method="post">
    <div class="mb-3">
        <label class="form-label">Username</label>
        <input type="text" name="username" class="form-control" value="<%= user.getUsername() %>" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="email" name="email" class="form-control" value="<%= user.getEmail() %>" required>
    </div>

    <!-- Button mở form đổi mật khẩu -->
    <div class="d-flex flex-column align-items-start">
    <button type="button" class="btn btn-warning mb-3" id="changePasswordBtn" onclick="togglePasswordFields()">Change Password</button>

    <!-- Các input nhập mật khẩu, ban đầu ẩn -->
    <div id="passwordFields" style="display: none;">
        <div class="mb-3">
            <label class="form-label">Current Password</label>
            <input type="password" name="currentPassword" class="form-control" placeholder="Enter current password">
        </div>
        <div class="mb-3">
            <label class="form-label">New Password</label>
            <input type="password" name="newPassword" class="form-control" placeholder="Enter new password">
        </div>
        <div class="mb-3">
            <label class="form-label">Confirm New Password</label>
            <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm new password">
        </div>
    </div>

    <!-- Nút Save Changes, ban đầu ẩn -->
    <button type="submit" class="btn btn-primary" id="saveChangesBtn" style="display: none;">Save Changes</button>
    <button id="themeToggleBtn" class="btn btn-secondary">🌙 Dark Mode</button>
    </div>

</form>
        </div>
    </body>
</html>
