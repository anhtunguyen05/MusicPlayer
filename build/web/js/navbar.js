/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", function () {
    const navLinks = document.querySelectorAll(".nav-link");
    const libLinks = document.querySelector(".library");
    console.log(libLinks);
    // Lấy đường dẫn hiện tại của trang web
    const currentPath = window.location.pathname;
    const currentParam = window.location.search;
    console.log(currentPath + currentParam);
    
    
    navLinks.forEach(link => {
        // Nếu đường dẫn của thẻ <a> trùng với đường dẫn hiện tại => thêm class 'active'
        if (link.getAttribute("href") === currentPath + currentParam) {
            link.classList.add("active");
        } else {
            link.classList.remove("active");
        }

        // Khi click vào bất kỳ link nào, active sẽ được cập nhật
        link.addEventListener("click", function () {
            // Xóa 'active' của tất cả link
            navLinks.forEach(l => l.classList.remove("active"));

            // Thêm 'active' cho link được click
            this.classList.add("active");
        });
    });
    
    if(currentPath == "/MusicPlayer/LibraryServlet"){
        libLinks.classList.add("active");
    }
});
