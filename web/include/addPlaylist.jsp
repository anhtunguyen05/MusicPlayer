<%-- 
    Document   : addPlaylist
    Created on : Mar 6, 2025, 4:08:41 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/add_playlist.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="playlist-container">
                <div class="header">
                <a href="#">Add to playlist</a>
                <a href="#" class="active">Create a playlist</a>
            </div>

            <form action="processPlaylist.jsp" method="post">
                <label for="playlistTitle" class="form-label">Playlist title <span style="color:red;">*</span></label>
                <input type="text" id="playlistTitle" name="playlistName" class="form-control" required>

                <div class="mt-3">
                    <label class="form-label">Privacy:</label>
                    <div class="privacy-options">
                        <input type="radio" name="privacy" value="public" checked> Public
                        <input type="radio" name="privacy" value="private"> Private
                    </div>
                </div>

                <button type="submit" class="btn btn-save mt-3">Save</button>
            </form>

            
        </div>
    </body>
</html>
