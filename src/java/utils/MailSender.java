/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Date;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.UUID;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;



/**
 *
 * @author pc
 */
public class MailSender {
     private static final String EMAIL = "anhtunguyen643@gmail.com";  // Thay bằng email của bạn
    private static final String PASSWORD = "eimj lsmt kowf annx";  // Mật khẩu ứng dụng (App Password)

    public static void sendVerificationEmail(String recipientEmail, String verificationLink) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
            message.setSubject("Xác nhận đăng ký tài khoản");
            message.setSentDate(new Date());
            message.setText("Nhấp vào liên kết sau để xác nhận tài khoản: " + verificationLink);

            Transport.send(message);
            System.out.println("Email xác nhận đã được gửi.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
         String verificationCode = UUID.randomUUID().toString(); 
        MailSender.sendVerificationEmail("de180945nguyengocthien@gmail.com", "datngu"+verificationCode);
    }
}
