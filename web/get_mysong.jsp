<%-- 
    Document   : get_mysong
    Created on : Mar 6, 2025, 8:44:47 PM
    Author     : pc
--%>

<%@ page contentType="application/json; charset=UTF-8" %>
<%@ page import="org.json.simple.JSONArray, org.json.simple.JSONObject" %>
<%@ page import="java.sql.Connection, dbcontext.ConnectDB" %>
<%@ page import="java.sql.PreparedStatement, java.sql.ResultSet" %>

<%
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
        Integer userId = (Integer) session.getAttribute("user_id");

        if (userId == null) {
            JSONObject error = new JSONObject();
            error.put("error", "User not logged in");
            out.print(error.toJSONString());
            out.flush();
            return;
        }

    Connection conn = ConnectDB.getInstance().openConnection();
    
    JSONArray songList = new JSONArray();
    
    // Lấy danh sách bài hát của playlist
    String songsQuery = "SELECT song_id, song_name, artist, file_url, song_img FROM Songs WHERE user_id = ?";
    PreparedStatement psSongs = conn.prepareStatement(songsQuery);
    psSongs.setInt(1, userId);
    ResultSet rsSongs = psSongs.executeQuery();
    int count = 1;
    while (rsSongs.next()) {
         JSONObject song = new JSONObject();
            song.put("song_id", rsSongs.getString("song_id"));
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
