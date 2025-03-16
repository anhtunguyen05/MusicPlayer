/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function toggleMenu(optionBtn) {
    // Đóng tất cả menu trước khi mở menu mới
    var allMenus = document.getElementsByClassName("playlist-menu");
    for (var i = 0; i < allMenus.length; i++) {
        if (allMenus[i] !== optionBtn.nextElementSibling) {
            allMenus[i].classList.remove("show");
        }
    }

    // Lấy menu của phần tử vừa bấm và bật/tắt hiển thị
    var menu = optionBtn.nextElementSibling;
    if (menu) {
        menu.classList.toggle("show");
    }
}

// Đóng menu khi bấm ra ngoài
document.onclick = function (event) {
    var isOption = event.target.classList.contains("option");
    var isMenu = event.target.classList.contains("playlist-menu");

    if (!isOption || !isMenu) {
        var menus = document.getElementsByClassName("playlist-menu");
        for (var i = 0; i < menus.length; i++) {
            menus[i].classList.remove("show");
        }
    }
};

function showPlaylistForm(event, songId) {
    event.stopPropagation(); // Ngăn sự kiện lan ra ngoài
    document.getElementById("playlistForm").style.display = "block";
    document.querySelectorAll(".add-to-playlist").forEach(button => {
        button.setAttribute("data-song-id", songId);
    });


}

// Đóng form
function closePlaylistForm() {
    document.getElementById("playlistForm").style.display = "none";
}

document.onclick = function (event) {
    var form = document.getElementById("playlistForm");
    if (!form.contains(event.target)) {
        closePlaylistForm();
    }
};

document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".add-to-playlist").forEach(button => {
        button.addEventListener("click", function (event) {
            event.preventDefault(); // Ngăn chặn chuyển trang

            let songId = this.getAttribute("data-song-id");
            let playlistId = this.getAttribute("data-playlist-id");

            fetch("PlaylistServlet", {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: `action=add&songId=${songId}&playlistId=${playlistId}`
            })
                    .then(response => response.text())
                    .then(data => {
                        if (data.trim() === "success") {
                            alert("Đã thêm vào playlist!");
                        } else {
                            alert("Lỗi: " + data);
                        }
                    })
                    .catch(error => console.error("Error:", error));
        });
    });

    document.querySelectorAll(".create-playlist").forEach(button => {
        button.addEventListener("click", function (event) {
            event.preventDefault(); // Ngăn chặn chuyển trang

            var playlistName = document.querySelector("#create-playlist").value;

            fetch("PlaylistServlet", {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: `action=create&playlistName=${playlistName}`
            })
                    .then(response => response.text())
                    .then(data => {
                        if (data.trim() === "success") {
                            alert("Đã thêm vào playlist!");
                        } else {
                            alert("Lỗi: " + data);
                        }
                        location.reload();
                    })
                    .catch(error => console.error("Error:", error));
        });
    });

});

function removeFromPlaylist(event, songId, playlistId) {
    event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ a hoặc button

    if (!songId) {
        console.error("Lỗi: Thiếu songId");
        return;
    }

    let url, bodyData;

    if (playlistId == 0) {
        // Xóa bài hát người dùng đăng
        console.log("Gửi request đến SongServlet...", playlistId);
        url = "SongServlet";
        bodyData = `command=remove&songId=${songId}`;
    } else {
        // Xóa bài hát khỏi playlist
        console.log("Gửi request đến PlaylistServlet...", playlistId);
        url = "PlaylistServlet";
        bodyData = `action=remove&songId=${songId}&playlistId=${playlistId}`;
    }
    console.log("URL:", url, "Body:", bodyData); // Log request để debug
    fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: bodyData
    })
            .then(response => response.text())
            .then(data => {
                console.log(data);
                location.reload(); // Tải lại trang sau khi xóa thành công
            })
            .catch(error => console.error("Lỗi:", error));
}

function deletePlaylist(playlistId) {
    if (confirm("Bạn có chắc chắn muốn xóa playlist này không?")) {
        fetch('PlaylistServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: `action=delete&playlist_id=${playlistId}`
        })
                .then(response => response.text())
                .then(data => {

                    window.location.href = 'HomepageServlet';

                });
    }
}

function openEditForm() {
    document.getElementById("edit-form-container").classList.remove("hidden");
 }

function closeEditForm() {
    document.getElementById("edit-form-container").classList.add("hidden");
}
  
 function savePlaylist(playlistId) {
        const newName = document.getElementById("edit-playlist-name").value;
        
        fetch('PlaylistServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `action=update&playlist_id=${playlistId}&new_name=${newName}`
        })
        .then(response => response.text())
        .then(data => {
           
            //document.getElementById("playlist-name").innerText = newName;
            closeEditForm();
            location.reload();
        });
    }