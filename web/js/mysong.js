/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const playlistId = new URLSearchParams(window.location.search).get("playlist_id");


const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const PLAYER_STORAGE_KEY = "F8_PLAYER";

const player = $(".player");
const headingSong = $("header h2");
const headingArtist = $("header h4");
const cd = $(".cd");
const cdThumb = $(".cd-thumb");
const audio = $("#audio");
const playBtn = $(".btn-toggle-play");
const progress = $("#progress");
const prevBtn = $(".btn-prev");
const nextBtn = $(".btn-next");
const randomBtn = $(".btn-random");
const repeatBtn = $(".btn-repeat");
const playlist = $(".playlist");
const volume = $("#volume");
const mute = $("#mute");
const dashboard = $(".dashboard");
const currentTime = $("#current-time");
const duration = $("#duration");





const app = {
  currentIndex: 0,
  isPlaying: false,
  isRandom: false,
  isRepeat: false,
  config: JSON.parse(localStorage.getItem(PLAYER_STORAGE_KEY)) || {},

    songs: [],
  setConfig: function (key, value) {
    this.config[key] = value;
    localStorage.setItem(PLAYER_STORAGE_KEY, JSON.stringify(this.config));
  },
  
  loadSongs: function(){
      fetch(`get_mysong.jsp`)
          .then(response => response.json())
          .then(data => {
               console.log("Dữ liệu từ API:", data); // Kiểm tra dữ liệu nhận được
                if (Array.isArray(data) && data.length > 0) {
                    this.songs = data;
                    console.log("Danh sách bài hát đã cập nhật:", this.songs);
                    this.startAfterLoading(); 
                } else {
                    console.error("Lỗi: Không có bài hát nào trong danh sách.");
                }
                 
                this.startAfterLoading();       
            }) 
            .catch(error => console.error("Lỗi tải danh sách bài hát:", error));
  },
  
  render: function () {
    const htmls = this.songs.map((song, index) => {
      return `
                  <div class="song ${
                    index === this.currentIndex ? "active" : ""
                  }" data-index=${index}>
                    <div
                      class="thumb"
                      style="
                        background-image: url('${song.song_img}');
                      "
                    ></div>
                    <div class="body">
                      <h3 class="title">${song.song_name}</h3>
                      <p class="author">${song.artist}</p>
                    </div>
                    <div class="option">
                      <i class="fas fa-ellipsis-h" onclick="toggleMenu(this)"></i>
                      <div class="playlist-menu" onclick="showPlaylistForm(event)" >Add to play list</div>
                    </div>
                  </div>`;
    });
    playlist.innerHTML = htmls.join("");
  },

  defineProperties: function () {
    Object.defineProperty(this, "currentSong", {
      get: function () {
        return this.songs[this.currentIndex];
      },
    });
  },

  handleEvents: function () {
    const _this = this;

    // Xử lí CD quay
    const cdThumbAnimate = cdThumb.animate(
      [
        {
          transform: "rotate(360deg)",
        },
      ],
      {
        duration: 10000,
        iterations: Infinity,
      }
    );
    cdThumbAnimate.pause();

    // Xử lí khi click play
    playBtn.onclick = () => {
    if (this.isPlaying) {
      audio.pause();
    } else {
      audio.play();
    }
  };
    // Khi song play
    audio.onplay = () => {
      this.isPlaying = true;
      player.classList.add("playing");
      cdThumbAnimate.play();
    };

    // Khi song pause
    audio.onpause = () =>  {
      this.isPlaying = false;
      player.classList.remove("playing");
      cdThumbAnimate.pause();
    };

    // Khi tiến độ bài hát thay đổi
    audio.ontimeupdate = function () {
      if (audio.duration) {
        const progressPercent = Math.floor(
          (audio.currentTime / audio.duration) * 100
        );
        progress.value = progressPercent;
        currentTime.textContent = _this.calculateTime(audio.currentTime);
        duration.textContent = _this.calculateTime(audio.duration);
      }
    };

    // Xử lí khi tua song
    progress.oninput = function (e) {
      const seekTime = audio.duration * (e.target.value / 100);
      audio.currentTime = seekTime;
    };

    // Khi next song
     nextBtn.onclick = () => {
      if (_this.isRandom) {
        this.playRandomSong();
      } else {
        this.currentIndex++;
        if (this.currentIndex >= this.songs.length) {
          this.currentIndex = 0;
        }
        this.loadCurrentSong();
        audio.play();
        this.render();
        this.scrollToActiveSong();
      }
    };

    // Khi prev song
     prevBtn.onclick = () => {
      if (audio.currentTime >= 5) {
        audio.currentTime = 0;
        audio.play();
      } else {
        if (_this.isRandom) {
          this.playRandomSong();
        } else {
          this.currentIndex--;
          if (this.currentIndex < 0) {
            this.currentIndex = _this.songs.length - 1;
          }
          this.loadCurrentSong();
          audio.play();
          this.render();
          this.scrollToActiveSong();
        }
      }
    };

    // Khi random songs
    randomBtn.onclick = () => {
      this.isRandom = !this.isRandom;
      this.setConfig("isRandom", this.isRandom);
      randomBtn.classList.toggle("active", this.isRandom);
    };

    // Xử lí repeat lại song
    repeatBtn.onclick = () => {
      this.isRepeat = !this.isRepeat;
      this.setConfig("isRepeat", this.isRepeat);
      repeatBtn.classList.toggle("active", this.isRepeat);
    };

    // Xử lí next song khi audio ended
    audio.onended = () => {
       this.isRepeat ? audio.play() : nextBtn.click();
     };

    // Lắng nghe hành vi click vào playlist
      playlist.onclick = (e) =>  {
      const songNode = e.target.closest(".song:not(.active)");
      if (songNode || e.target.closest(".option")) {
        // Xử lí khi click vào song
        if (e.target.closest(".song:not(.active)")) {
//          _this.showPlayer();
          this.currentIndex = Number(songNode.dataset.index);
          this.loadCurrentSong();
          this.render();
          audio.play();
        }

        // Xử lí khi click vào option
        else if (e.target.closest(".option")) {
          console.log(123);
        }
      }
    };

    // Volume
    volume.oninput = function (e) {
      audio.volume = e.target.value / 100;
      if (audio.volume == 0) {
        mute.classList.remove("fa-volume-low");
        mute.classList.add("fa-volume-xmark");
      } else if (audio.volume < 0.5) {
        mute.classList.remove("fa-volume-high");
        mute.classList.remove("fa-volume-xmark");
        mute.classList.add("fa-volume-low");
      } else {
        mute.classList.remove("fa-volume-low");
        mute.classList.remove("fa-volume-xmark");
        mute.classList.add("fa-volume-high");
      }
    };

    mute.onclick = function (e) {
      audio.muted = !audio.muted;

      if (audio.muted === true) {
        mute.classList.remove("fa-volume-high");
        mute.classList.add("fa-volume-xmark");
      } else {
        mute.classList.remove("fa-volume-xmark");
        mute.classList.add("fa-volume-high");
      }
    };
  },

  // Xử lí click vào song sẽ hiện ra mục phát
  showPlayer: function () {
    dashboard.style.display = "flex";
  },

  calculateTime: function (secs) {
    const minutes = Math.floor(secs / 60);
    const seconds = Math.floor(secs % 60);
    const returnedSeconds = seconds < 10 ? `0${seconds}` : `${seconds}`;
    return `${minutes}:${returnedSeconds}`;
  },

  loadCurrentSong: function () {
      if (!this.songs || this.songs.length === 0) {
        console.error("Danh sách bài hát trống, không thể load bài hát hiện tại.");
        return;
    }
    headingArtist.textContent = this.currentSong.artist;
    headingSong.textContent = this.currentSong.song_name;
    cdThumb.style.backgroundImage = `url('${this.currentSong.song_img}')`;
    audio.src = this.currentSong.file_url;
  },

  loadConfig: function () {
    this.isRandom = this.config.isRandom;
    this.isRepeat = this.config.isRepeat;
  },

  playRandomSong: function () {
    let newIndex;
    do {
      newIndex = Math.floor(Math.random() * this.songs.length);
    } while (newIndex === this.currentIndex);

    this.currentIndex = newIndex;
    this.loadCurrentSong();
    audio.play();
    this.render();
    this.scrollToActiveSong();
  },

  scrollToActiveSong: function () {
    setTimeout(() => {
      $(".song.active").scrollIntoView({
        behavior: "smooth",
        // block: `${this.currentIndex == 0 ? "center" : "nearest"}`,
        block: "center",
      });
    }, 300);
  },
  startAfterLoading:  function () {
      
     if (!this.songs || this.songs.length === 0) {
        console.error("Danh sách bài hát trống.");
        return;
    }
      
    // Gán cấu hình từ config vào ứng dụng
    this.loadConfig();

    // Định nghĩa các thuộc tính cho object
    this.defineProperties();

    // Lắng nghe / xử lí các sự kiện (DOM Events)
    this.handleEvents();

    // Tải thông tin bài hát đầu tiên vào UI khi chạy app
    this.loadCurrentSong();
  
    //
    // this.nextSong();

    // Render playlist
    this.render();

    //
    randomBtn.classList.toggle("active", this.isRandom);
    repeatBtn.classList.toggle("active", this.isRepeat);
  },
    start: function () {
      this.loadSongs();  // Chờ tải xong rồi mới chạy tiếp
    },
};
app.start();

