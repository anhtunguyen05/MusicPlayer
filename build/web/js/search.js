/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function searchSong() {
    let keyword = document.getElementById("searchBox").value;
    if (keyword.length === 0) {
        document.getElementById("suggestionList").innerHTML = "";
        return;
    }

    fetch("GetSearchNameServlet?keyword=" + encodeURIComponent(keyword))
            .then(response => response.json())
            .then(data => {
                
                let html = "<ul>";
                data.forEach(song => {
                    console.log(song.name)
                    html += `<li onclick="selectSong('${song.name}')">${song.name} - ${song.artist}</li>`;
                });
                html += "</ul>";
                document.getElementById("suggestionList").innerHTML = html;
            })
            .catch(error => console.error("Lỗi tìm kiếm:", error));
}

function selectSong(name) {
    document.getElementById("searchBox").value = name;
    document.getElementById("suggestionList").innerHTML = "";
}