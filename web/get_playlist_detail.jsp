<%-- 
    Document   : get_playlist_detail
    Created on : Mar 2, 2025, 3:00:49 AM
    Author     : pc
--%>

<%@ page contentType="application/json; charset=UTF-8" %>
<%@ page import="org.json.simple.JSONArray, org.json.simple.JSONObject" %>
<%@ page import="java.sql.Connection, dbContext.ConnectDB" %>
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

    // Lấy thông tin playlist
    String playlistQuery = "SELECT name, description FROM Playlists WHERE playlist_id = ? " ;
    PreparedStatement psPlaylist = conn.prepareStatement(playlistQuery);
    psPlaylist.setInt(1, Integer.parseInt(playlistId));
    ResultSet rsPlaylist = psPlaylist.executeQuery();

    String playlistName = "";
    String playlistDescription = "";

    if (rsPlaylist.next()) {
        playlistName = rsPlaylist.getString("name");
        playlistDescription = rsPlaylist.getString("description");
    } else {
         JSONObject error = new JSONObject();
        error.put("error", "Playlist not found");
        out.print(error.toString());
        out.flush();
        return;
    }
    
    JSONArray songList = new JSONArray();
    
    // Lấy danh sách bài hát của playlist
    String songsQuery = "SELECT s.song_id, s.title, s.artist, s.file_url, s.cover_url " +
                        "FROM Playlist_Songs ps " +
                        "JOIN Songs s ON ps.song_id = s.song_id " +
                        "WHERE ps.playlist_id = ? ";
    PreparedStatement psSongs = conn.prepareStatement(songsQuery);
    psSongs.setInt(1, Integer.parseInt(playlistId));
    ResultSet rsSongs = psSongs.executeQuery();
    int count = 1;
    while (rsSongs.next()) {
         JSONObject song = new JSONObject();
            song.put("title", rsSongs.getString("title"));
            song.put("artist", rsSongs.getString("artist"));
            song.put("file_url", rsSongs.getString("file_url"));
            song.put("cover_url", rsSongs.getString("cover_url") != null ? rsSongs.getString("cover_url") : "https://i.ytimg.com/vi/jTLhQf5KJSc/maxresdefault.jpg");

            songList.add(song);
    }
    conn.close();
    out.print(songList.toJSONString());
    out.flush();
%>
