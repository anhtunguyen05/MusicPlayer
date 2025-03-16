<%-- 
    Document   : addSong
    Created on : Mar 2, 2025, 10:26:34 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <%@ page session="true" %>
        <%
            Boolean isVip = (Boolean) session.getAttribute("isVip");
            if (isVip != null && isVip) {
        %>
        <script>
            alert("Congratulations! You are now a VIP Member! Enjoy exclusive access to our premium services. 🎉");
        </script>
        <%
                session.removeAttribute("isVip"); // Xóa cờ để không hiển thị lại thông báo
            }
        %>

        <div class="container mt-5">
            <h2 class="text-center">🎶 Upload Nhạc Mới</h2>

            <form action="SongServlet" method="post" enctype="multipart/form-data" class="p-4 border rounded bg-light">
                <div class="mb-3">
                    <label for="title" class="form-label">Tên bài hát</label>
                    <input type="text" class="form-control" id="song_name" name="song_name" required>
                </div>

                <div class="mb-3">
                    <label for="artist" class="form-label">Ca sĩ</label>
                    <input type="text" class="form-control" id="artist" name="artist" required>
                </div>

                <div class="mb-3">
                    <label for="genre" class="form-label">Thể loại</label>
                    <input type="text" class="form-control" id="genre" name="genre">
                </div>

                <div class="mb-3">
                    <label for="file" class="form-label">Chọn file nhạc (MP3)</label>
                    <input type="file" class="form-control" id="file" name="file" accept=".mp3" required>
                </div>

                <div class="mb-3">
                    <label for="cover" class="form-label">Chọn ảnh bìa</label>
                    <input type="file" class="form-control" id="cover" name="cover" accept="image/*">
                </div>

                <input type="submit" name="command" value="add" class="btn btn-primary w-100">📤 Tải lên</button>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
