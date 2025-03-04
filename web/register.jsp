<%-- 
    Document   : register
    Created on : Feb 15, 2025, 12:14:54 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/css/validator.css" />
        <title>Sign up</title>
    </head>
   <body class="bg-light">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <h3 class="text-center">Đăng ký</h3>
                    <form action="RegisterServlet" method="POST" id="form-1" >
                        <div class="form-group mb-3">
                            <label for="username" class="form-label"><h6>Nhập tên tài khoản:</h6></label>
                          <input
                            id="username"
                            name="username"
                            rules="required"
                            type="text"
                            placeholder="VD: Anhtu1210"
                            class="form-control"
                          />
                          <span class="form-message"></span>
                        </div>

                        <div class="form-group mb-3">
                            <label for="email" class="form-label"><h6>Email</h6></label>
                          <input
                            id="email"
                            name="email"
                            rules="required|email"
                            type="text"
                            placeholder="VD: email@domain.com"
                            class="form-control"
                          />
                          <span class="form-message"></span>
                        </div>

                        <div class="form-group mb-3">
                            <label for="password" class="form-label"><h6>Mật khẩu</h6></label>
                          <input
                            id="password"
                            name="password"
                            rules="required|min:6"
                            type="password"
                            placeholder="Nhập mật khẩu"
                            class="form-control"
                          />
                          <span class="form-message"></span>
                        </div>

                        <div class="form-group mb-3">
                          <label for="password_confirmation" class="form-label"
                                 ><h6>Nhập lại mật khẩu</h6></label
                          >
                          <input
                            id="password_confirmation"
                            name="password_confirmation"
                            rules="required|confirmed"
                            placeholder="Nhập lại mật khẩu"
                            type="password"
                            class="form-control"
                          />
                          <span class="form-message"></span>
                        </div>
                        
                        <button type="submit" class="btn btn-success w-100">Đăng ký</button>
                    </form>
                    <p class="text-center mt-3">Đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>
                </div>
            </div>
        </div>
       <script src="./js/validator.js"></script>
       <script>
      var form = new Validator("#form-1");
       </script>
    </body>
</html>
