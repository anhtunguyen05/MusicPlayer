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
                        <span  style="margin-left: 10px; display: block;">My Playlist</span>
                    </div>
                    <div>
                        <%
                     Integer userId = (Integer) session.getAttribute("user_id");
                      if (userId == null) {
                        return;
                    }
                     
                     try(Connection conn = ConnectDB.getInstance().openConnection()) {
                        
                        // Lấy danh sách playlist cùng số lượng bài hát
                        String sql = "SELECT playlist_id, playlist_name, playlist_img FROM Playlists WHERE user_id = ?";
                        
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setInt(1, userId);
                        ResultSet rs = ps.executeQuery();
                        
                        while (rs.next()) {
                     %>   
                     
                     <div class="music-item">
                        <img src="song-cover.jpg" alt="Song Cover" class="song-cover">
                        <div class="song-info">
                            <span class="song-title"><%= rs.getString("playlist_name") %></span>
                        </div>
                        <button class="add-to-playlist" data-playlist-id="<%= rs.getInt("playlist_id") %>" data-song-id="">
                            Add
                        </button>
                    </div>
                     <%
                            }
                           } 
                     %>
                    </div>
                    <button type="submit" name="command" value="ADD" class="button" style="margin-left: auto;">Add to Playlist</button>
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
