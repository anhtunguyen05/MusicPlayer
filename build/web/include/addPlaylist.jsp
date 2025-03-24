<%-- 
    Document   : addPlaylist
    Created on : Mar 6, 2025, 4:08:41 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, dbcontext.ConnectDB" %>
<%@ page import="java.sql.PreparedStatement, java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>JSP Page</title>
        
       <link rel="stylesheet" href="./assets/css/addPlaylist.css" />
    </head>
    <body>
            <div class="modal" id="playlistForm">
                <div class="tabs">
                    <div class="tab actives" onclick="switchTab('add')">Add to playlist</div>
                    <div class="tab" onclick="switchTab('create')">Create a playlist</div>
                </div>

                <form id="add" class="content actives">
                    <input type="text" placeholder="Filter playlists" class="filter"">
                    <div style="display: flex; align-items: center; margin-top: 10px;">
                        <span class="title-playlist">My Playlist</span>
                    </div>
                    <div>
                       <%
    String userID = (String) session.getAttribute("user_id");
    String songId = (String) request.getAttribute("song_id");

    // Nếu songId vẫn null, có thể lấy từ request parameter
    if (songId == null) {
        songId = request.getParameter("song_id");
    }

    // Nếu vẫn null sau tất cả, gán giá trị trống hoặc ID không hợp lệ (-1)
    if (songId == null || songId.isEmpty()) {
        songId = "-1"; // Giá trị không hợp lệ thay vì "1"
    }

    if (userID == null) {
        return;
    }

    try (Connection conn = ConnectDB.getInstance().openConnection()) {
        // Lấy danh sách playlist CHƯA có bài hát này
        String sql = "SELECT p.playlist_id, p.playlist_name, p.playlist_img " +
                     "FROM Playlists p " +
                     "WHERE p.user_id = ? " +  
                     "AND NOT EXISTS (SELECT 1 FROM Playlist_Songs ps WHERE ps.playlist_id = p.playlist_id AND ps.song_id = ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(userID));
        ps.setInt(2, Integer.parseInt(songId));

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
%>   

<div class="music-item">
    <div class="song-info">
        <span class="song-title"><%= rs.getString("playlist_name") %></span>
    </div>
    <button class="add-to-playlist" data-playlist-id="<%= rs.getInt("playlist_id") %>" data-song-id="<%= songId %>">
        Add
    </button>
</div>

<%
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>


                    </div>
                   
                </form>

                <form id="create" class="content">
                    <input id="create-playlist" type="text" placeholder="Playlist title" class="filter">
                    <button type="submit" name="command" value="ADD" class="button create-playlist" style="margin-top: 10px;">Save</button>
                </form>
            </div>
        <script>
          function switchTab(tab) {
                document.querySelectorAll('.tab').forEach(el => el.classList.remove('actives'));
                document.querySelectorAll('.content').forEach(el => el.classList.remove('actives'));

                const tabs = document.querySelectorAll('.tab');
                if (tab === 'add') {
                    tabs[0].classList.add('actives');
                } else {
                    tabs[1].classList.add('actives');
                }
                document.getElementById(tab)?.classList.add('actives');
            }
        </script>
    </body>
</html>
