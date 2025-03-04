<%-- 
    Document   : verfidy
    Created on : Mar 4, 2025, 10:28:56 PM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Xác thực tài khoản</h2>
        <p>Mã xác thực đã được gửi đến email của bạn.</p>

        <form action="VerifyServlet" method="post" onsubmit="return submitOTP()">
            <label>Nhập mã OTP:</label>
            <input id="username" name="username" type="text"/>
            <input type="text" name="code" id="code" required>
            <button type="submit">Xác nhận</button>
        </form>
        <script>
            function submitOTP() {
                let otp = document.getElementById("otp").value;
                if (otp.length !== 6) {
                    alert("Mã OTP phải có 6 chữ số!");
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
