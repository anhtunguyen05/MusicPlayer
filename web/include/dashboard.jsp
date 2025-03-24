<%-- 
    Document   : dashboard
    Created on : Mar 18, 2025, 8:26:53 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/css/playlist_detail.css" />
        <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
          integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
          crossorigin="anonymous"
        />
        <link
          rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css"
        />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
          href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
          rel="stylesheet"
        />
    </head>
    <body>
        <div class="dashboard player">
            <!-- Header -->
            <div class="dashboard__left">
              <header>
                <h2>Now playing:</h2>
                <h4>String 57th & 9th</h4>
              </header>

              <!-- CD -->
              <div class="cd">
                <div
                  class="cd-thumb"
                  style="
                    background-image: url('https://i.ytimg.com/vi/jTLhQf5KJSc/maxresdefault.jpg');
                  "
                ></div>
              </div>
            </div>

            <div class="dashboard__center">
              <!-- Control -->
              <div class="control">
                <div class="btn btn-repeat">
                  <i class="fas fa-redo"></i>
                </div>
                <div class="btn btn-prev">
                  <i class="fas fa-step-backward"></i>
                </div>
                <div class="btn btn-toggle-play">
                  <i class="fa-solid fa-pause icon-pause"></i>
                  <i class="fa-solid fa-play icon-play"></i>
                </div>
                <div class="btn btn-next">
                  <i class="fas fa-step-forward"></i>
                </div>
                <div class="btn btn-random">
                  <i class="fas fa-random"></i>
                </div>
              </div>

              <!-- song progress -->
              <form id="adTime">
                <span id="current-time" class="time">0:00</span>
                <input
                  id="progress"
                  class="progress"
                  type="range"
                  value="0"
                  step="1"
                  min="0"
                  max="100"
                />
                <span id="duration" class="time">0:00</span>
              </form>
            </div>

            <div class="dashboard_right">
                <div class="btn btn-tym "> 
                    <i class="fa-solid fa-heart"></i>
                </div>
            <form id="adVolume" action="">
                <i id="mute" class="fa-solid fa-volume-high"></i>
                <input
                  id="volume"
                  class="progress"
                  type="range"
                  value="100"
                  step="1"
                  min="0"
                  max="100"
                />
          </form>
        </div>

            <audio id="audio" src=""></audio>
          </div>
        </div>
    </body>
</html>
