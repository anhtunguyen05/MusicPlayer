<%-- 
    Document   : updatePlaylist
    Created on : Mar 13, 2025, 12:47:06 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/updatePlaylist.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div id="edit-form-container" class="edit-form hidden">
            <h3>Chỉnh sửa Playlist</h3>
            <input type="text" id="edit-playlist-name" value="<%= request.getAttribute("playlistName") %>">
            <button class="save-btn" onclick="savePlaylist(<%= request.getParameter("playlist_id") %>)">Lưu</button>
            <button class="cancel-btn" onclick="closeEditForm()">Hủy</button>
        </div>
           
    </body>
</html>
