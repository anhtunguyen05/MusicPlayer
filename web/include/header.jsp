<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/css/homepage.css"/>
        <title>JSP Page</title>
    </head>
    <body>
<<<<<<< HEAD
        <%@ page session="true" %>
        <%
            String username = (String) session.getAttribute("username");
        %>
=======
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
          <div class="container-fluid">
              <a class="navbar-brand" href="#"><img class="logo" src="./assets/img/logo.png" alt="alt"/></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="./mysong.jsp">Library</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./UploadServlet">Up songs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./login.jsp">Login</a>
                </li>
              </ul>
              <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
              </form>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                     <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                          User
<!--                          <img src="./img/user-icon-on-transparent-background-free-png.webp" alt="alt"/>-->
                        </a>
                        <ul class="dropdown-menu">
                          <li><a class="dropdown-item" href="#">Profile</a></li>
                          <li><a class="dropdown-item" href="#">Another action</a></li>
                          <li><hr class="dropdown-divider"></li>
                          <li><a class="dropdown-item" href="#">Logout</a></li>
                        </ul>
                  </li>
                </ul>
>>>>>>> 943125c29b3da1343a172d8e02d7b4806436af94

        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><img class="logo" src="./assets/img/logo.png" alt="alt"/></a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>

                        <li class="nav-item">
                            <%
                                if (username != null) { // Nếu đã đăng nhập
                            %>
                            <a class="nav-link" href="./mysong.jsp">Library</a>
                            <%
                                } else { // Nếu chưa đăng nhập
                            %>
                            <a class="nav-link" href="login.jsp" onclick="return confirm('Bạn cần đăng nhập để có thư viện riêng!');">Library</a>
                            <%
                                }
                            %>
                        </li>
                        <li class="nav-item">
                            <%
                                if (username != null) { // Nếu đã đăng nhập
                            %>
                            <a class="nav-link" href="./addSong.jsp">Up songs</a>
                            <%
                                } else { // Nếu chưa đăng nhập
                            %>
                            <a class="nav-link" href="login.jsp" onclick="return confirm('Bạn cần đăng nhập để đăng bài hát!');">Up songs</a>
                            <%
                                }
                            %>
                        </li>
                    </ul>

                    <!-- Search bar căn giữa -->
                    <div class="mx-auto w-50">
                        <form class="d-flex" role="search">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>
                    </div>

                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <%
                            if (username != null) {  // Nếu đã đăng nhập
                        %>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <%= username %>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="profile.jsp">Profile</a></li>
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="LogoutServlet">Logout</a></li>
                            </ul>
                        </li>
                        <%
                            } else {  // Nếu chưa đăng nhập, hiển thị nút Login và Register
                        %>
                        <li class="nav-item">
                            <a class="btn btn-primary me-2" href="login.jsp">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-success" href="register.jsp">Register</a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </nav>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
