<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.json.simple.JSONArray, org.json.simple.JSONObject" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account Upgrade Payment</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
            }

            h1 {
                color: #AD915F;
                text-align: center;
                margin-top: 10px ;
                margin-bottom: 20px ;
                font-weight: 600;
            }

            h4 {
                color: red;
                font-size: 16px
            }

            .container {
                display: flex;
                justify-content: space-between;
                width: 80%;
                max-width: 1200px;
                margin-top: 30px;
            }

            .membership-details,
            .qr-code {
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 48%;
                text-align: center;
            }

            .membership-details {
                margin-right: 20px;
            }

            .qr-code {
                display: none;
            }

            .membership-details h2 {
                margin-bottom: 20px;
                color: #333;
            }

            .benefits {
                text-align: center;
                margin-bottom: 20px;
            }

            .benefits ul {
                list-style-type: none;
                padding: 0;
            }

            .benefits li {
                margin-bottom: 10px;
                font-size: 20px;
                color: #666;
                margin-top: 20px;
                font-weight: 300;
            }

            .price {
                font-size: 20px;
                font-weight: bold;
                margin-bottom: 30px;
            }

            .price span {
                font-size: 16px;
                color: #666;
            }

            #qr-img {
                width: 250px;
                height: auto;
            }

            .btn-upgrade {
                background-color: #AD915F;
                color: white;
                padding: 12px 30px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 20px;
            }

            .btn-upgrade:hover {
                background-color: #4a3b6f;
            }
        </style>
    </head>

    <body>
        <h1>Upgrade to VIP Membership to Unlock Premium Features!</h1>


        <!-- Membership details and QR code section -->
        <div class="row">
            <!-- Membership details -->
            <div class="membership-details col-md-6">
                <h3>VIP Membership - 20,000₫ </h3>

                <div class="benefits">
                    <ul>
                        <li>Unlimited uploads</li>
                        <li>Access listening history</li>
                        <li>Like your favorite songs</li>
                    </ul>
                </div>

                <button class="btn-upgrade" onclick="showQRCode()">Get Started</button>
            </div>

            <!-- QR Code for payment -->
            <div id="qr-code" class="qr-code col-md-6">
                <h3>Scan the QR code to make a payment:</h3>
                <h4>Note: Please make sure you enter the correct account and transfer content.</h4>
                <img id="qr-img" src="" alt="QR Code">
            </div>
        </div>

        <script src="./js/payment.js"></script>
        
    </body>

</html>
