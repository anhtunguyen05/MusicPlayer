<%-- 
    Document   : profile
    Created on : Mar 11, 2025, 2:08:58 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAO.UserDAO, model.User" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Profile</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="./assets/css/profile.css">
    </head>
    <body>
        <%@include file="/include/header.jsp"%>
        <%
            String userID = (String) session.getAttribute("user_id"); 
            if (userID == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            UserDAO userDao = new UserDAO();
            User user = userDao.getUserById(userID);
            String img = (user.getAvatarUrl() != null) ? user.getAvatarUrl() : "song_img/anhst1.jpg"; 
        %>     

        <div class="container ">
            <div class="row justify-content-center">
                <div class="col-md-12 ">
                    <div class="card p-4 shadow-lg text-white " style=";background: linear-gradient(135deg, #667eea, #764ba2);">
                        <div class="card-body d-flex align-items-center">
                            <div class="col-2 text-center me-3 position-relative">
                                <img src="<%= img %>" alt="Avatar" class="rounded-circle avatar-img">

                                <div class="upload-container  position-relative d-flex justify-content-center">
                                    <% if (img.equals("song_img/avatar.jpg")) { %>
                                    <!-- Trường hợp ảnh mặc định: Tự động upload khi chọn file -->
                                    <form action="UploadAvatarServlet" method="post" enctype="multipart/form-data">
                                        <input type="file" name="avatar" class="form-control d-none" id="fileInput" onchange="this.form.submit();">
                                        <button type="button" class="btn btn-light btn-sm d-flex align-items-center gap-1 " onclick="document.getElementById('fileInput').click();">
                                            <img src="song_img/mayAnh.jpg" alt="Camera Icon" class="img-fluid" style="width: 20px; height: 20px;">
                                            Upload Image
                                        </button>
                                    </form>
                                    <% } else { %>
                                    <!-- Trường hợp đã có ảnh: Hiển thị menu chọn hành động -->
                                    <button type="button" class="btn btn-light btn-sm d-flex align-items-center gap-1 mt-2" onclick="toggleMenu()">
                                        <img src="song_img/mayAnh.jpg" alt="Camera Icon" class="img-fluid" style="width: 20px; height: 20px;">
                                        Upload Image
                                    </button>

                                    <!-- Menu upload cố định dưới avatar -->
                                    <div id="uploadMenu" class="upload-menu">
                                        <form action="UploadAvatarServlet" method="post" enctype="multipart/form-data">
                                            <input type="file" name="avatar" class="form-control d-none" id="fileInput">
                                            <button type="button" class="btn btn-primary btn-sm w-100" onclick="document.getElementById('fileInput').click();">
                                                Chọn ảnh mới
                                            </button>
                                        </form>

                                        <form action="UploadAvatarServlet" method="post" class="mt-2">
                                            <input type="hidden" name="action" value="delete">
                                            <button type="submit" class="btn btn-danger btn-sm w-100">Xóa ảnh</button>
                                        </form>
                                    </div>
                                    <% } %>
                                </div>

                                <script>
                                    function toggleMenu() {
                                        let menu = document.getElementById('uploadMenu');
                                        menu.style.display = (menu.style.display === 'none' || menu.style.display === '') ? 'block' : 'none';
                                    }
                                </script>
                            </div>

                            <div class="col-8 ">
                                <h4>UserName: <strong><%= user.getUsername() %></strong> </h4>
                                <h4>Email: <strong><%= user.getEmail() %></strong> </h4>
                                <h4>VIP: <strong><%= user.isVip() ? "Yes" : "No" %></strong> </h4>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
