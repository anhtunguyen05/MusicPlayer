function generateHex() {
    // Generate a random 8-character hexadecimal string
    return Math.floor(Math.random() * 0xFFFFFFF).toString(16).padStart(8, '0').toUpperCase();
}

function showQRCode() {
    let mybank = {
        BANK_ID: "MB",
        ACCOUNT_NO: "0856082228",
        ACCOUNT_NAME: "NGUYEN DINH PHAN TRUNG",
        price: 20000
    };
    let des = generateHex(); // Generate a random description
    console.log(des);
    let qrCodeUrl = `https://img.vietqr.io/image/MB-0856082228-compact2.png?amount=${mybank.price}&addInfo=${des}&accountName=NGUYEN%20DINH%20PHAN%20TRUNG`;
    document.getElementById('qr-img').src = qrCodeUrl;
    document.getElementById('qr-code').style.display = 'block';
    checkPaid(mybank.price, des);
}

async function checkPaid(price, content) {
    // Lưu trữ intervalId để có thể dừng việc kiểm tra sau khi thanh toán thành công
    let intervalId;

    setTimeout(() => {
        // Gọi hàm checkPayment sau 10 giây
        checkPayment(price, content, intervalId);

        // Lặp lại việc kiểm tra thanh toán mỗi 5 giây
        intervalId = setInterval(() => {
            checkPayment(price, content, intervalId);
        }, 5000);

    }, 10000); // Bắt đầu kiểm tra sau 10 giây
}

async function checkPayment(price, content, intervalId) {
    try {
        const response = await fetch(
                "https://script.google.com/macros/s/AKfycbyOlxbVXUpaAUHGeSHZ_zycr2KALEtdVP438S9cOWZT6m4QvDJ_1VjD22tHxx2QsE1JBQ/exec"
                );
        const data = await response.json();
        const lastPaid = data.data[data.data.length - 1];
        const lastPrice = lastPaid["Giá trị"];
        const lastContent = lastPaid["Mô tả"];

        // Kiểm tra nếu thanh toán thành công
        if (lastPrice >= price && lastContent.includes(content)) {
            alert("Thanh toán thành công");

            // Gửi yêu cầu cập nhật trạng thái VIP của người dùng lên server
            await fetch("UpdateVipStatusServlet", {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: "updateVip=true"
            });

            // Dừng việc kiểm tra sau khi thanh toán thành công
            clearInterval(intervalId);

            // Chuyển hướng về trang addSong.jsp sau 2 giây
            setTimeout(() => {
                window.location.href = "addSong.jsp";
            }, 2000);
        } else {
            console.log("Không thành công");
        }
    } catch {
        console.error("Lỗi");
    }
}
