<%-- 
    Document   : homepage
    Created on : Feb 16, 2025, 10:53:36 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, dbcontext.ConnectDB" %>
<%@ page import="java.sql.PreparedStatement, java.sql.ResultSet" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/include/header.jsp"%>
        <div class="container mt-4">
            <h2 class="text-center text-primary">Danh sách Playlist</h2>
            <div class="row">
                <%
                    try(Connection conn = ConnectDB.getInstance().openConnection()) {
                        
                        // Lấy danh sách playlist cùng số lượng bài hát
                        String sql = "SELECT p.playlist_id, p.playlist_name, COUNT(ps.song_id) as song_count " +
                                     "FROM Playlists p LEFT JOIN Playlist_Songs ps ON p.playlist_id = ps.playlist_id " +
                                     "GROUP BY p.playlist_id, p.playlist_name";

                        PreparedStatement ps = conn.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        
                        while (rs.next()) {
                %> 
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title"><%= rs.getString("playlist_name") %></h5>
                            <p><strong>Số bài hát:</strong> <%= rs.getInt("song_count") %></p>
                            <a href="playlist_detail.jsp?playlist_id=<%= rs.getInt("playlist_id") %>" class="btn btn-primary">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
                <%
                        }
                    } catch (Exception e) {
                        out.println("<p class='text-danger'>Lỗi khi lấy danh sách playlist: " + e.getMessage() + "</p>");
                    }
                %>
            </div>
        </div>
    </body>
</html>
