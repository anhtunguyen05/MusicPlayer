/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.querySelectorAll('.playlist-item').forEach(item => {
    item.addEventListener('click', function (e) {
        const playButton = e.target.closest('.play-button');

        if (playButton) {
            // Logic phát bài nhạc
            console.log("Phát nhạc");
            // Thêm logic play nhạc ở đây
        }
    });
});