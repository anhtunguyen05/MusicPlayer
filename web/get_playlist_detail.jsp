<%-- 
    Document   : get_playlist_detail
    Created on : Mar 2, 2025, 3:00:49 AM
    Author     : pc
--%>

<%@ page contentType="application/json; charset=UTF-8" %>
<%@ page import="org.json.simple.JSONArray, org.json.simple.JSONObject" %>
<%@ page import="java.sql.Connection, dbcontext.ConnectDB" %>
<%@ page import="java.sql.PreparedStatement, java.sql.ResultSet" %>

<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    // Lấy playlist_id từ URL
    String playlistId = request.getParameter("playlist_id");
    if (playlistId == null) {
        JSONObject error = new JSONObject();
        error.put("error", "Missing playlist_id");
        out.print(error.toJSONString());
        out.flush();
        return;
        
    }

    Connection conn = ConnectDB.getInstance().openConnection();
    
    JSONArray songList = new JSONArray();
    
    // Lấy danh sách bài hát của playlist
    String songsQuery = "SELECT s.song_id, s.song_name, s.artist, s.file_url, s.song_img " +
                        "FROM Playlist_Songs ps " +
                        "JOIN Songs s ON ps.song_id = s.song_id " +
                        "WHERE ps.playlist_id = ? ";
    PreparedStatement psSongs = conn.prepareStatement(songsQuery);
    psSongs.setInt(1, Integer.parseInt(playlistId));
    ResultSet rsSongs = psSongs.executeQuery();
    int count = 1;
    while (rsSongs.next()) {
         JSONObject song = new JSONObject();
            song.put("song_name", rsSongs.getString("song_name"));
            song.put("artist", rsSongs.getString("artist"));
            song.put("file_url", rsSongs.getString("file_url"));
            song.put("song_img", rsSongs.getString("song_img") != null ? rsSongs.getString("song_img") : "https://i.ytimg.com/vi/jTLhQf5KJSc/maxresdefault.jpg");

            songList.add(song);
    }
    conn.close();
    out.print(songList.toJSONString());
    out.flush();
%>
