document.addEventListener("DOMContentLoaded", function () {
    const themeToggleBtn = document.getElementById("themeToggleBtn");
    const body = document.body;

    // Kiểm tra trạng thái theme đã lưu
    if (localStorage.getItem("theme") === "dark") {
        body.classList.add("dark-mode");
        if (themeToggleBtn) themeToggleBtn.textContent = "☀ Light Mode";
    }

    // Khi nhấn nút đổi theme
    if (themeToggleBtn) {
        themeToggleBtn.addEventListener("click", function () {
            body.classList.toggle("dark-mode");

            // Lưu trạng thái vào localStorage
            if (body.classList.contains("dark-mode")) {
                localStorage.setItem("theme", "dark");
                themeToggleBtn.textContent = "☀ Light Mode";
            } else {
                localStorage.setItem("theme", "light");
                themeToggleBtn.textContent = "🌙 Dark Mode";
            }
        });
    }
});
